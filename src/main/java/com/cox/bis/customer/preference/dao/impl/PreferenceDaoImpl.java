package com.cox.bis.customer.preference.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.cox.bis.customer.preference.dao.PreferenceDao;
import com.cox.bis.customer.preference.db.util.StoredProcedureBean;
import com.cox.bis.customer.preference.db.util.StoredProcedureBeanForJava;
import com.cox.bis.customer.preference.icoms.bean.Credential;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveMethodRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveMethodResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrievePreferenceRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrievePreferenceResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveUnassignSMSMethodsRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveUnassignSMSMethodsResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateAddressTypeRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateAddressTypeResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateMethodRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateMethodResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdatePreferenceRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdatePreferenceResponse;
import com.cox.bis.customer.preference.icoms.sql.model.IcomsSqlContext;
import com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod;
import com.cox.bis.customer.preference.icoms.sql.model.NotificationPreference;
import com.cox.bis.customer.preference.icoms.sql.model.UnassignedSMSMobileNumber;
import com.cox.bis.customer.preference.util.SimpleCrypto;
import com.cox.bis.customer.preference.util.LocalConstants.IcomsSqlConstants;

@Component
public class PreferenceDaoImpl implements PreferenceDao {

	@Autowired
	@Qualifier(value = "icomsAs400Sql")
	javax.sql.DataSource icomsAs400Sql;
	
	@Autowired
	@Qualifier(value = "icomsSql")
	javax.sql.DataSource icomsSql;
	
	@Autowired
	private Environment env;
	
	@Override
	public Credential icomsCredential(String clientId){
		Credential credential = new Credential();
		try (Connection con = icomsSql.getConnection()) {
			String preparedStatement = "{? = call spGetIcomsCredentialByClientId(?,?,?)}"; 

			int parameter1 = Types.INTEGER;
			int parameter3 = Types.VARCHAR;
			int parameter4 = Types.VARCHAR;
			try (CallableStatement stmt = con.prepareCall(preparedStatement)) {
				stmt.registerOutParameter(1, parameter1);
				stmt.setString(2, clientId);
				stmt.registerOutParameter(3, parameter3);
				stmt.registerOutParameter(4, parameter4);
				stmt.executeUpdate();
				credential.setUsername(stmt.getString(3));
				credential.setPassword(stmt.getString(4));
				credential.setDecryptedPassword(SimpleCrypto.decrypt(stmt.getString(4)));
				credential.setIsValidated(stmt.getInt(1)==0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return credential;
	}
	
	private List<Map<String, String>> retrieveCustomerNotificationMethodParams(){
		
		List<Map<String, String>> listOfMap = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "APPNAME");
		map1.put("mode", "in");
		map1.put("type", "VARCHAR");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "ENVNAME");
		map2.put("mode", "in");
		map2.put("type", "VARCHAR");

		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "SITEID");
		map3.put("mode", "in");
		map3.put("type", "NUMERIC");
		
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("name", "CUSTNUM");
		map4.put("mode", "in");
		map4.put("type", "NUMERIC");
		
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("name", "SWAPUSERID");
		map5.put("mode", "in");
		map5.put("type", "VARCHAR");
		
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("name", "NUMBEROFRECORDS");
		map6.put("mode", "in");
		map6.put("type", "NUMERIC");
		
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("name", "MORERECORDSEXIST");
		map7.put("mode", "out");
		map7.put("type", "VARCHAR");
		
		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("name", "RETURNCODE");
		map8.put("mode", "out");
		map8.put("type", "VARCHAR");
		
		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("name", "MESSAGETEXT");
		map9.put("mode", "out");
		map9.put("type", "VARCHAR");
		
		listOfMap.add(map1);
		listOfMap.add(map2);
		listOfMap.add(map3);
		listOfMap.add(map4);
		listOfMap.add(map5);
		listOfMap.add(map6);
		listOfMap.add(map7);
		listOfMap.add(map8);
		listOfMap.add(map9);
		
		return listOfMap;
	}
	
	private Map<String, Object> prepareInParameters(Object request) throws Exception {
		Map<String, Object> inParameters = new HashMap<>();
		Field[] fields = request.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (fieldName.equals("serialVersionUID")) continue;
			String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method method = request.getClass().getDeclaredMethod(methodName);
			Object value = ReflectionUtils.invokeMethod(method, request);
			inParameters.put(field.getName(), value);
		}
		return inParameters;
	}

	@Override
	public CustomerNotificationRetrieveMethodResponse retrieveCustomerNotificationMethod(
			CustomerNotificationRetrieveMethodRequest request) throws Exception {
		CustomerNotificationRetrieveMethodResponse response = new CustomerNotificationRetrieveMethodResponse();
		List<NotificationMethod> notificationMethodList = new ArrayList<>();
		//CommonsDataSource dataSource = getAssignedDataSource(request);
		
		StoredProcedureBeanForJava storedProcedureBean = new StoredProcedureBeanForJava(icomsAs400Sql,
				env.getProperty("icoms.sql.schema.name").concat(".").concat(IcomsSqlConstants.RETRIEVE_CUST_NOT_METHOD_SP_NAME), false,
				retrieveCustomerNotificationMethodParams());
		Map<String, Object> inParameters = prepareInParameters(request);
		try {
			Map<String, Object> outputData = storedProcedureBean.executeStoredProcedure(inParameters);
			response.setReturnCode(((String) outputData.get("RETURNCODE")).trim());
			response.setMessageText(((String) outputData.get("MESSAGETEXT")).trim());
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map<String, Object>> dataList = (List) outputData.get("#result-set-1");
			if (dataList != null) {
				for (@SuppressWarnings("rawtypes")
				Map dataItem : dataList) {
					NotificationMethod notificationMethod = new NotificationMethod();
					if (dataItem.get("RSNOTFMETH") != null)
						notificationMethod.setMethod(dataItem.get("RSNOTFMETH").toString().trim());
					if (dataItem.get("RSNOTFADDR") != null)
						notificationMethod.setAddressType(dataItem.get("RSNOTFADDR").toString().trim());
					if (dataItem.get("RSADDRESS") != null)
						notificationMethod.setAddress(dataItem.get("RSADDRESS").toString().trim());
					if (dataItem.get("RSMOBILE") != null)
						notificationMethod.setIsMobile(dataItem.get("RSMOBILE").toString().trim());
					if (dataItem.get("RSPRIMRY") != null)
						notificationMethod.setIsPrimary(dataItem.get("RSPRIMRY").toString().trim());
					if (dataItem.get("RSNOTFOPN") != null)
						notificationMethod.setServiceOptIn(dataItem.get("RSNOTFOPN").toString().trim());
					if (dataItem.get("RSOPTION") != null)
						notificationMethod.setIsEditable(dataItem.get("RSOPTION").toString().trim());
					if (dataItem.get("RSVERYSTS") != null)
						notificationMethod.setVerificationStatus(dataItem.get("RSVERYSTS").toString().trim());
					if (dataItem.get("RSVERYDESC") != null)
						notificationMethod
								.setVerificationStatusDescription(dataItem.get("RSVERYDESC").toString().trim());
					if (dataItem.get("RSVERYDTE") != null)
						notificationMethod.setVerificationDate(dataItem.get("RSVERYDTE").toString().trim());
					if (dataItem.get("RSCONFMSTS") != null)
						notificationMethod.setConfirmStatus(dataItem.get("RSCONFMSTS").toString().trim());
					if (dataItem.get("RSCONFRMDTE") != null)
						notificationMethod.setConfirmDate(dataItem.get("RSCONFRMDTE").toString().trim());
					if (dataItem.get("RSMETHCRTUSR") != null)
						notificationMethod.setCreatedBy(dataItem.get("RSMETHCRTUSR").toString().trim());
					if (dataItem.get("RSMETHCRTDTE") != null)
						notificationMethod.setCreatedDate(dataItem.get("RSMETHCRTDTE").toString().trim());
					if (dataItem.get("RSMETHCRTTIM") != null)
						notificationMethod.setCreatedTime(dataItem.get("RSMETHCRTTIM").toString().trim());
					if (dataItem.get("RSMETHCHGUSR") != null)
						notificationMethod.setChangedBy(dataItem.get("RSMETHCHGUSR").toString().trim());
					if (dataItem.get("RSMETHCHGDTE") != null)
						notificationMethod.setChangedDate(dataItem.get("RSMETHCHGDTE").toString().trim());
					if (dataItem.get("RSMETHCHGTIM") != null)
						notificationMethod.setChangedTime(dataItem.get("RSMETHCHGTIM").toString().trim());
					if (dataItem.get("RSMOBCRTUSR") != null)
						notificationMethod.setCreatedByMobile(dataItem.get("RSMOBCRTUSR").toString().trim());
					if (dataItem.get("RSMOBCRTDTE") != null)
						notificationMethod.setCreatedDateMobile(dataItem.get("RSMOBCRTDTE").toString().trim());
					if (dataItem.get("RSMOBCRTTIM") != null)
						notificationMethod.setCreatedTimeMobile(dataItem.get("RSMOBCRTTIM").toString().trim());
					if (dataItem.get("RSMOBCHGUSR") != null)
						notificationMethod.setChangedByMobile(dataItem.get("RSMOBCHGUSR").toString().trim());
					if (dataItem.get("RSMOBCHGDTE") != null)
						notificationMethod.setChangedDateMobile(dataItem.get("RSMOBCHGDTE").toString().trim());
					if (dataItem.get("RSMOBCHGTIM") != null)
						notificationMethod.setChangedTimeMobile(dataItem.get("RSMOBCHGTIM").toString().trim());
					notificationMethodList.add(notificationMethod);
				}
			}
			response.setNotificationMethods(notificationMethodList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}	
	
	private List<Map<String, String>> updateCustomerNotificationMethodParams(){
		
		List<Map<String, String>> listOfMap = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "APPNAME");
		map1.put("mode", "in");
		map1.put("type", "VARCHAR");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "ENVNAME");
		map2.put("mode", "in");
		map2.put("type", "VARCHAR");

		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "SITEID");
		map3.put("mode", "in");
		map3.put("type", "NUMERIC");
		
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("name", "CUSTNUM");
		map4.put("mode", "in");
		map4.put("type", "NUMERIC");
		
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("name", "NOTFMETHOD");
		map5.put("mode", "in");
		map5.put("type", "VARCHAR");
		
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("name", "NOTFADDR");
		map6.put("mode", "in");
		map6.put("type", "VARCHAR");
		
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("name", "ADDRESS");
		map7.put("mode", "in");
		map7.put("type", "VARCHAR");
		
		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("name", "MOBILE");
		map8.put("mode", "in");
		map8.put("type", "VARCHAR");
		
		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("name", "PRIMARY");
		map9.put("mode", "in");
		map9.put("type", "VARCHAR");
		
		Map<String, String> map10 = new HashMap<String, String>();
		map10.put("name", "NOTFOPTN");
		map10.put("mode", "in");
		map10.put("type", "VARCHAR");
		
		Map<String, String> map11 = new HashMap<String, String>();
		map11.put("name", "VERIFYSTS");
		map11.put("mode", "in");
		map11.put("type", "VARCHAR");
		
		Map<String, String> map12 = new HashMap<String, String>();
		map12.put("name", "CONFIRMSTS");
		map12.put("mode", "in");
		map12.put("type", "VARCHAR");
		
		Map<String, String> map13 = new HashMap<String, String>();
		map13.put("name", "SWAPUSERID");
		map13.put("mode", "in");
		map13.put("type", "VARCHAR");
		
		Map<String, String> map14 = new HashMap<String, String>();
		map14.put("name", "RETURNCODE");
		map14.put("mode", "out");
		map14.put("type", "VARCHAR");
		
		Map<String, String> map15 = new HashMap<String, String>();
		map15.put("name", "MESSAGETEXT");
		map15.put("mode", "out");
		map15.put("type", "VARCHAR");
	
		listOfMap.add(map1);
		listOfMap.add(map2);
		listOfMap.add(map3);
		listOfMap.add(map4);
		listOfMap.add(map5);
		listOfMap.add(map6);
		listOfMap.add(map7);
		listOfMap.add(map8);
		listOfMap.add(map9);
		listOfMap.add(map10);
		listOfMap.add(map11);
		listOfMap.add(map12);
		listOfMap.add(map13);
		listOfMap.add(map14);
		listOfMap.add(map15);
		
		return listOfMap;
	}
	
	@Override
	public CustomerNotificationUpdateMethodResponse updateCustomerNotificationMethod(
			CustomerNotificationUpdateMethodRequest request, IcomsSqlContext context) throws Exception {
		CustomerNotificationUpdateMethodResponse response = new CustomerNotificationUpdateMethodResponse();
		List<NotificationMethod> notificationMethodList = new ArrayList<>();
		//CommonsDataSource dataSource = getAssignedDataSource(request);
		
		StoredProcedureBeanForJava storedProcedureBean = new StoredProcedureBeanForJava(icomsAs400Sql,
				env.getProperty("icoms.sql.schema.name").concat(".").concat(IcomsSqlConstants.UPDATE_CUST_NOT_METHOD_SP_NAME), false,
				updateCustomerNotificationMethodParams());
		Map<String, Object> inParameters = prepareInParameters(request);
		try {
			Map<String, Object> outputData = storedProcedureBean.executeStoredProcedure(inParameters);
			response.setReturnCode(((String) outputData.get("RETURNCODE")).trim());
			response.setMessageText(((String) outputData.get("MESSAGETEXT")).trim());
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map<String, Object>> dataList = (List) outputData.get("#result-set-1");
			if (dataList != null) {
				for (@SuppressWarnings("rawtypes")
				Map dataItem : dataList) {
					NotificationMethod notificationMethod = new NotificationMethod();
					if (dataItem.get("RSNOTFMETH") != null)
						notificationMethod.setMethod(dataItem.get("RSNOTFMETH").toString().trim());
					if (dataItem.get("RSNOTFADDR") != null)
						notificationMethod.setAddressType(dataItem.get("RSNOTFADDR").toString().trim());
					if (dataItem.get("RSADDRESS") != null)
						notificationMethod.setAddress(dataItem.get("RSADDRESS").toString().trim());
					if (dataItem.get("RSMOBILE") != null)
						notificationMethod.setIsMobile(dataItem.get("RSMOBILE").toString().trim());
					if (dataItem.get("RSPRIMARY") != null)
						notificationMethod.setIsPrimary(dataItem.get("RSPRIMARY").toString().trim());
					if (dataItem.get("RSNOTFOPN") != null)
						notificationMethod.setServiceOptIn(dataItem.get("RSNOTFOPN").toString().trim());
					if (dataItem.get("RSVERYSTS") != null)
						notificationMethod.setVerificationStatus(dataItem.get("RSVERYSTS").toString().trim());
					if (dataItem.get("RSVERYDATE") != null)
						notificationMethod.setVerificationDate(dataItem.get("RSVERYDATE").toString().trim());
					if (dataItem.get("RSVERYTIME") != null)
						notificationMethod.setVerificationTime(dataItem.get("RSVERYTIME").toString().trim());
					if (dataItem.get("RSCONFMSTS") != null)
						notificationMethod.setConfirmStatus(dataItem.get("RSCONFMSTS").toString().trim());
					if (dataItem.get("RSCONFRMDATE") != null)
						notificationMethod.setConfirmDate(dataItem.get("RSCONFRMDATE").toString().trim());
					if (dataItem.get("RSCONFRMTIME") != null)
						notificationMethod.setConfirmTime(dataItem.get("RSCONFRMTIME").toString().trim());
					if (dataItem.get("RSCREATEUSER") != null)
						notificationMethod.setCreatedBy(dataItem.get("RSCREATEUSER").toString().trim());
					if (dataItem.get("RSCREATEDATE") != null)
						notificationMethod.setCreatedDate(dataItem.get("RSCREATEDATE").toString().trim());
					if (dataItem.get("RSCREATETIME") != null)
						notificationMethod.setCreatedTime(dataItem.get("RSCREATETIME").toString().trim());
					if (dataItem.get("RSCHANGEUSER") != null)
						notificationMethod.setChangedBy(dataItem.get("RSCHANGEUSER").toString().trim());
					if (dataItem.get("RSCHANGEDATE") != null)
						notificationMethod.setChangedDate(dataItem.get("RSCHANGEDATE").toString().trim());
					if (dataItem.get("RSCHANGETIME") != null)
						notificationMethod.setChangedTime(dataItem.get("RSCHANGETIME").toString().trim());
					notificationMethodList.add(notificationMethod);
				}
			}
			response.setNotificationMethods(notificationMethodList);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return response;
	}
	
	private List<Map<String, String>> updateCustomerNotificationPreferenceParams(){
		
		List<Map<String, String>> listOfMap = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "APPNAME");
		map1.put("mode", "in");
		map1.put("type", "VARCHAR");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "ENVNAME");
		map2.put("mode", "in");
		map2.put("type", "VARCHAR");

		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "SITEID");
		map3.put("mode", "in");
		map3.put("type", "NUMERIC");
		
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("name", "CUSTNUM");
		map4.put("mode", "in");
		map4.put("type", "NUMERIC");
		
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("name", "NOTFCATG");
		map5.put("mode", "in");
		map5.put("type", "VARCHAR");
		
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("name", "NOTFMETH");
		map6.put("mode", "in");
		map6.put("type", "VARCHAR");
		
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("name", "NOTFOPTN");
		map7.put("mode", "in");
		map7.put("type", "VARCHAR");
		
		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("name", "SWAPUSERID");
		map8.put("mode", "in");
		map8.put("type", "VARCHAR");
		
		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("name", "RETURNCODE");
		map9.put("mode", "out");
		map9.put("type", "VARCHAR");
		
		Map<String, String> map10 = new HashMap<String, String>();
		map10.put("name", "MESSAGETEXT");
		map10.put("mode", "out");
		map10.put("type", "VARCHAR");
		
		
		listOfMap.add(map1);
		listOfMap.add(map2);
		listOfMap.add(map3);
		listOfMap.add(map4);
		listOfMap.add(map5);
		listOfMap.add(map6);
		listOfMap.add(map7);
		listOfMap.add(map8);
		listOfMap.add(map9);
		listOfMap.add(map10);
		
		return listOfMap;
	}
	
	@Override
	public CustomerNotificationUpdatePreferenceResponse updateCustomerNotificationPreference(
			CustomerNotificationUpdatePreferenceRequest request, IcomsSqlContext context) throws Exception {
		CustomerNotificationUpdatePreferenceResponse response = new CustomerNotificationUpdatePreferenceResponse();
		List<NotificationPreference> notificationPreferenceList = new ArrayList<>();
		//CommonsDataSource dataSource = getAssignedDataSource(request);

		StoredProcedureBean storedProcedureBean = new StoredProcedureBean(icomsAs400Sql,
				env.getProperty("icoms.sql.schema.name").concat(".").concat(IcomsSqlConstants.UPDATE_CUST_NOT_PREFERENCES_SP_NAME), false,
				updateCustomerNotificationPreferenceParams());
		Map<String, Object> inParameters = prepareInParameters(request);
		try {
			Map<String, Object> outputData = storedProcedureBean.execute(inParameters);
			response.setReturnCode(((String) outputData.get("RETURNCODE")).trim());
			response.setMessageText(((String) outputData.get("MESSAGETEXT")).trim());
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map<String, Object>> dataList = (List) outputData.get("#result-set-1");
			if (dataList != null) {
				for (@SuppressWarnings("rawtypes")
				Map dataItem : dataList) {
					NotificationPreference notificationPreference = new NotificationPreference();
					if (dataItem.get("RSNTFCATG") != null)
						notificationPreference.setCategory(dataItem.get("RSNTFCATG").toString().trim());
					if (dataItem.get("RSNTFMETH") != null)
						notificationPreference.setMethod(dataItem.get("RSNTFMETH").toString().trim());
					if (dataItem.get("RSNTFOPTN") != null)
						notificationPreference.setOptIn(dataItem.get("RSNTFOPTN").toString().trim());
					if (dataItem.get("RSCREATEUSER") != null)
						notificationPreference.setCreatedBy(dataItem.get("RSCREATEUSER").toString().trim());
					if (dataItem.get("RSCREATEDATE") != null)
						notificationPreference.setCreatedDate(dataItem.get("RSCREATEDATE").toString().trim());
					if (dataItem.get("RSCREATETIME") != null)
						notificationPreference.setCreatedTime(dataItem.get("RSCREATETIME").toString().trim());
					if (dataItem.get("RSCHANGEUSER") != null)
						notificationPreference.setChangedBy(dataItem.get("RSCHANGEUSER").toString().trim());
					if (dataItem.get("RSCHANGEDATE") != null)
						notificationPreference.setChangedDate(dataItem.get("RSCHANGEDATE").toString().trim());
					if (dataItem.get("RSCHANGETIME") != null)
						notificationPreference.setChangedTime(dataItem.get("RSCHANGETIME").toString().trim());
					notificationPreferenceList.add(notificationPreference);
				}
			}
			response.setNotificationPreferences(notificationPreferenceList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private List<Map<String, String>> updateCustomerMethodAddressTypeParams(){
		
		List<Map<String, String>> listOfMap = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "APPNAME");
		map1.put("mode", "in");
		map1.put("type", "VARCHAR");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "ENVNAME");
		map2.put("mode", "in");
		map2.put("type", "VARCHAR");

		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("name", "SITEID");
		map3.put("mode", "in");
		map3.put("type", "NUMERIC");
		
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("name", "CUSTNUM");
		map4.put("mode", "in");
		map4.put("type", "NUMERIC");
		
		Map<String, String> map5 = new HashMap<String, String>();
		map5.put("name", "NOTFMETHOD");
		map5.put("mode", "in");
		map5.put("type", "VARCHAR");
		
		Map<String, String> map6 = new HashMap<String, String>();
		map6.put("name", "NOTFADDR1");
		map6.put("mode", "in");
		map6.put("type", "VARCHAR");
		
		Map<String, String> map7 = new HashMap<String, String>();
		map7.put("name", "NOTFADDR2");
		map7.put("mode", "in");
		map7.put("type", "VARCHAR");
		
		Map<String, String> map8 = new HashMap<String, String>();
		map8.put("name", "SWAPUSERID");
		map8.put("mode", "in");
		map8.put("type", "VARCHAR");
		
		Map<String, String> map9 = new HashMap<String, String>();
		map9.put("name", "RETURNCODE");
		map9.put("mode", "out");
		map9.put("type", "VARCHAR");
		
		Map<String, String> map10 = new HashMap<String, String>();
		map10.put("name", "MESSAGETEXT");
		map10.put("mode", "out");
		map10.put("type", "VARCHAR");
		
		
		listOfMap.add(map1);
		listOfMap.add(map2);
		listOfMap.add(map3);
		listOfMap.add(map4);
		listOfMap.add(map5);
		listOfMap.add(map6);
		listOfMap.add(map7);
		listOfMap.add(map8);
		listOfMap.add(map9);
		listOfMap.add(map10);
		
		return listOfMap;
	}
	
	@Override
	public CustomerNotificationUpdateAddressTypeResponse updateCustomerMethodAddressType(
			CustomerNotificationUpdateAddressTypeRequest request, IcomsSqlContext context) throws Exception {
		CustomerNotificationUpdateAddressTypeResponse response = new CustomerNotificationUpdateAddressTypeResponse();
		//CommonsDataSource dataSource = getAssignedDataSource(request);
		
		List<NotificationMethod> notificationMethodList = new ArrayList<>();
		StoredProcedureBeanForJava storedProcedureBean = new StoredProcedureBeanForJava(icomsAs400Sql,
				env.getProperty("icoms.sql.schema.name").concat(".").concat(IcomsSqlConstants.UPDATE_CUST_NOT_METHOD_ADDRESS_TYPE_SP_NAME),
				false, updateCustomerMethodAddressTypeParams());
		Map<String, Object> inParameters = prepareInParameters(request);
		try {
			Map<String, Object> outputData = storedProcedureBean.executeStoredProcedure(inParameters);
			response.setReturnCode(((String) outputData.get("RETURNCODE")).trim());
			response.setMessageText(((String) outputData.get("MESSAGETEXT")).trim());
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map<String, Object>> dataList = (List) outputData.get("#result-set-1");
			if (dataList != null) {
				for (@SuppressWarnings("rawtypes")
				Map dataItem : dataList) {
					NotificationMethod notificationMethod = new NotificationMethod();

					if (dataItem.get("RSNOTFMETH") != null)
						notificationMethod.setMethod(dataItem.get("RSNOTFMETH").toString().trim());
					if (dataItem.get("RSNOTFADDR") != null)
						notificationMethod.setAddressType(dataItem.get("RSNOTFADDR").toString().trim());
					if (dataItem.get("RSADDRESS") != null)
						notificationMethod.setAddress(dataItem.get("RSADDRESS").toString().trim());
					if (dataItem.get("RSMOBILE") != null)
						notificationMethod.setIsMobile(dataItem.get("RSMOBILE").toString().trim());
					if (dataItem.get("RSPRIMARY") != null)
						notificationMethod.setIsPrimary(dataItem.get("RSPRIMARY").toString().trim());
					if (dataItem.get("RSNOTFOPTN") != null)
						notificationMethod.setServiceOptIn(dataItem.get("RSNOTFOPTN").toString().trim());
					if (dataItem.get("RSVERYSTS") != null)
						notificationMethod.setVerificationStatus(dataItem.get("RSVERYSTS").toString().trim());
					if (dataItem.get("RSVERYDATE") != null)
						notificationMethod.setVerificationDate(dataItem.get("RSVERYDATE").toString().trim());
					if (dataItem.get("RSVERYTIME") != null)
						notificationMethod.setVerificationTime(dataItem.get("RSVERYTIME").toString().trim());
					if (dataItem.get("RSCONFMSTS") != null)
						notificationMethod.setConfirmStatus(dataItem.get("RSCONFMSTS").toString().trim());
					if (dataItem.get("RSCONFRMDATE") != null)
						notificationMethod.setConfirmDate(dataItem.get("RSCONFRMDATE").toString().trim());
					if (dataItem.get("RSCONFRMTIME") != null)
						notificationMethod.setConfirmTime(dataItem.get("RSCONFRMTIME").toString().trim());
					if (dataItem.get("RSCREATEUSER") != null)
						notificationMethod.setCreatedBy(dataItem.get("RSCREATEUSER").toString().trim());
					if (dataItem.get("RSCREATEDATE") != null)
						notificationMethod.setCreatedDate(dataItem.get("RSCREATEDATE").toString().trim());
					if (dataItem.get("RSCREATETIME") != null)
						notificationMethod.setCreatedTime(dataItem.get("RSCREATETIME").toString().trim());
					if (dataItem.get("RSCHANGEUSER") != null)
						notificationMethod.setChangedBy(dataItem.get("RSCHANGEUSER").toString().trim());
					if (dataItem.get("RSCHANGEDATE") != null)
						notificationMethod.setChangedDate(dataItem.get("RSCHANGEDATE").toString().trim());
					if (dataItem.get("RSCHANGETIME") != null)
						notificationMethod.setChangedTime(dataItem.get("RSCHANGETIME").toString().trim());
					notificationMethodList.add(notificationMethod);

				}
			}
			response.setNotificationMethods(notificationMethodList);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return response;
	}
	
	@Override
	public CustomerNotificationRetrievePreferenceResponse retrieveCustomerNotificationPreference(
			CustomerNotificationRetrievePreferenceRequest request) throws Exception {
		CustomerNotificationRetrievePreferenceResponse response = new CustomerNotificationRetrievePreferenceResponse();
		List<NotificationPreference> notificationPreferenceList = new ArrayList<>();
		StoredProcedureBeanForJava storedProcedureBean = new StoredProcedureBeanForJava(icomsAs400Sql,
				env.getProperty("icoms.sql.schema.name").concat(".").concat(IcomsSqlConstants.RETRIEVE_CUST_NOT_PREFERENCES_SP_NAME), false,
				retrieveCustomerNotificationMethodParams());
		Map<String, Object> inParameters = prepareInParameters(request);
		try {
			Map<String, Object> outputData = storedProcedureBean.executeStoredProcedure(inParameters);
			response.setReturnCode(((String) outputData.get("RETURNCODE")).trim());
			response.setMessageText(((String) outputData.get("MESSAGETEXT")).trim());
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map<String, Object>> dataList = (List) outputData.get("#result-set-1");
			if (dataList != null) {
				for (@SuppressWarnings("rawtypes")
				Map dataItem : dataList) {
					NotificationPreference notificationPreference = new NotificationPreference();
					if (dataItem.get("RSNOTFMETH") != null)
						notificationPreference.setMethod(dataItem.get("RSNOTFMETH").toString().trim());
					if (dataItem.get("RSNOTFMETHDSC") != null)
						notificationPreference.setMethodDescription(dataItem.get("RSNOTFMETHDSC").toString().trim());
					if (dataItem.get("RSNOTFADDRTYP") != null)
						notificationPreference.setAddressType(dataItem.get("RSNOTFADDRTYP").toString().trim());
					if (dataItem.get("RSNOTFCATG") != null)
						notificationPreference.setCategory(dataItem.get("RSNOTFCATG").toString().trim());
					if (dataItem.get("RSNOTFCATGDSC") != null)
						notificationPreference.setCategoryDescription(dataItem.get("RSNOTFCATGDSC").toString().trim());
					if (dataItem.get("RSNOTFOPN") != null)
						notificationPreference.setOptIn(dataItem.get("RSNOTFOPN").toString().trim());
					if (dataItem.get("RSALLOWED") != null)
						notificationPreference.setIsAllowed(dataItem.get("RSALLOWED").toString().trim());
					if (dataItem.get("RSEDITABLE") != null)
						notificationPreference.setIsEditable(dataItem.get("RSEDITABLE").toString().trim());
					if (dataItem.get("RSCREATEUSER") != null)
						notificationPreference.setCreatedBy(dataItem.get("RSCREATEUSER").toString().trim());
					if (dataItem.get("RSCREATEDATE") != null)
						notificationPreference.setCreatedDate(dataItem.get("RSCREATEDATE").toString().trim());
					if (dataItem.get("RSCREATETIME") != null)
						notificationPreference.setCreatedTime(dataItem.get("RSCREATETIME").toString().trim());
					if (dataItem.get("RSCHANGEUSER") != null)
						notificationPreference.setChangedBy(dataItem.get("RSCHANGEUSER").toString().trim());
					if (dataItem.get("RSCHANGEDATE") != null)
						notificationPreference.setChangedDate(dataItem.get("RSCHANGEDATE").toString().trim());
					if (dataItem.get("RSCHANGETIME") != null)
						notificationPreference.setChangedTime(dataItem.get("RSCHANGETIME").toString().trim());
					notificationPreferenceList.add(notificationPreference);
				}
			}
			response.setNotificationPreferences(notificationPreferenceList);
		} catch (Exception e) {
			throw e; // to be handled in the calling application
		}
		return response;
	}
	
	 private List<Map<String, String>> retrieveUnassignedSMSMethodsParams(){
			
			List<Map<String, String>> listOfMap = new ArrayList<Map<String, String>>();
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("name", "APPNAME");
			map1.put("mode", "in");
			map1.put("type", "VARCHAR");
			
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("name", "ENVNAME");
			map2.put("mode", "in");
			map2.put("type", "VARCHAR");

			Map<String, String> map3 = new HashMap<String, String>();
			map3.put("name", "SITEID");
			map3.put("mode", "in");
			map3.put("type", "NUMERIC");
			
			Map<String, String> map4 = new HashMap<String, String>();
			map4.put("name", "CUSTNUM");
			map4.put("mode", "in");
			map4.put("type", "NUMERIC");
			
			Map<String, String> map5 = new HashMap<String, String>();
			map5.put("name", "SWAPUSERID");
			map5.put("mode", "in");
			map5.put("type", "VARCHAR");
			
			Map<String, String> map6 = new HashMap<String, String>();
			map6.put("name", "NUMBEROFRECORDS");
			map6.put("mode", "in");
			map6.put("type", "NUMERIC");
			
			Map<String, String> map7 = new HashMap<String, String>();
			map7.put("name", "MORERECORDSEXIST");
			map7.put("mode", "out");
			map7.put("type", "VARCHAR");
			
			Map<String, String> map8 = new HashMap<String, String>();
			map8.put("name", "RETURNCODE");
			map8.put("mode", "out");
			map8.put("type", "VARCHAR");
			
			Map<String, String> map9 = new HashMap<String, String>();
			map9.put("name", "MESSAGETEXT");
			map9.put("mode", "out");
			map9.put("type", "VARCHAR");
			
			listOfMap.add(map1);
			listOfMap.add(map2);
			listOfMap.add(map3);
			listOfMap.add(map4);
			listOfMap.add(map5);
			listOfMap.add(map6);
			listOfMap.add(map7);
			listOfMap.add(map8);
			listOfMap.add(map9);
			
			return listOfMap;
		}
	
	@Override
	public CustomerNotificationRetrieveUnassignSMSMethodsResponse retrieveUnassignedSMSMethods(CustomerNotificationRetrieveUnassignSMSMethodsRequest request ) throws Exception {
		CustomerNotificationRetrieveUnassignSMSMethodsResponse response = new CustomerNotificationRetrieveUnassignSMSMethodsResponse();
		List<UnassignedSMSMobileNumber> unassignedSMSMobileNumberList = new ArrayList<>();
		StoredProcedureBeanForJava storedProcedureBean = new StoredProcedureBeanForJava(icomsAs400Sql,
				env.getProperty("icoms.sql.schema.name").concat(".").concat(IcomsSqlConstants.RETRIEVE_CUST_UNASSIGNED_SMS_SP_NAME), false, 
				retrieveUnassignedSMSMethodsParams());
		Map<String, Object> inParameters = prepareInParameters(request);
		try {
			Map<String, Object> outputData = storedProcedureBean.executeStoredProcedure(inParameters);
			response.setReturnCode(((String) outputData.get("RETURNCODE")).trim());
			response.setMessageText(((String) outputData.get("MESSAGETEXT")).trim());
			@SuppressWarnings({ "unchecked", "rawtypes" })
			List<Map<String, Object>> dataList = (List) outputData.get("#result-set-1");
			if (dataList != null) {
				for (@SuppressWarnings("rawtypes")
				Map dataItem : dataList) {
					UnassignedSMSMobileNumber unassignedSMSMobileNumber = new UnassignedSMSMobileNumber();

					if (dataItem.get("RSNOTFADDR") != null)
						unassignedSMSMobileNumber.setAddressType(dataItem.get("RSNOTFADDR").toString().trim());
					if (dataItem.get("RSADDRESS") != null)
						unassignedSMSMobileNumber.setAddress(dataItem.get("RSADDRESS").toString().trim());
					if (dataItem.get("RSCREATEUSER") != null)
						unassignedSMSMobileNumber.setCreatedBy(dataItem.get("RSCREATEUSER").toString().trim());
					if (dataItem.get("RSCREATEDATE") != null)
						unassignedSMSMobileNumber.setCreatedDate(dataItem.get("RSCREATEDATE").toString().trim());
					if (dataItem.get("RSCREATETIME") != null)
						unassignedSMSMobileNumber.setCreatedTime(dataItem.get("RSCREATETIME").toString().trim());
					if (dataItem.get("RSCHANGEUSER") != null)
						unassignedSMSMobileNumber.setChangedBy(dataItem.get("RSCHANGEUSER").toString().trim());
					if (dataItem.get("RSCHANGEDATE") != null)
						unassignedSMSMobileNumber.setChangedDate(dataItem.get("RSCHANGEDATE").toString().trim());
					if (dataItem.get("RSCHANGETIME") != null)
						unassignedSMSMobileNumber.setChangedTime(dataItem.get("RSCHANGETIME").toString().trim());
					unassignedSMSMobileNumberList.add(unassignedSMSMobileNumber);

				}
			}
			response.setUnassignedSMSMobileNumbers(unassignedSMSMobileNumberList);
		} catch (Exception e) {
			throw e; // to be handled in the calling application
		} 
		return response;
	}
}