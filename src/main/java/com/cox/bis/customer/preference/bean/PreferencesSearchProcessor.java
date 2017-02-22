package com.cox.bis.customer.preference.bean;



import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cox.bis.customer.preference.api.model.PreferencesSearchRequest;
import com.cox.bis.customer.preference.api.model.PreferencesSearchResponse;
import com.cox.bis.customer.preference.dao.impl.PreferenceDaoImpl;
import com.cox.bis.customer.preference.exception.BusinessException;
import com.cox.bis.customer.preference.icoms.bean.Credential;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveMethodRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveMethodResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrievePreferenceRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrievePreferenceResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveUnassignSMSMethodsRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveUnassignSMSMethodsResponse;
import com.cox.bis.customer.preference.model.NotificationMethod;
import com.cox.bis.customer.preference.model.NotificationPreference;
import com.cox.bis.customer.preference.model.Notifications;
import com.cox.bis.customer.preference.model.UnassignedSMSMobileNumber;
import com.cox.bis.customer.preference.util.LocalConstants;
import com.cox.bis.customer.preference.util.ValidationHelper;

@Component("preferencesSearchProcessor")
public class PreferencesSearchProcessor implements LocalConstants {

	@Autowired
	private PreferenceDaoImpl preferenceDaoImpl;

	@Autowired
	private Environment env;

	public PreferencesSearchResponse search(Exchange exchange) throws BusinessException, Exception {
		// output details that are to be shown summary, detail
		Credential credential = preferenceDaoImpl.icomsCredential("ibilluser");
		exchange.getIn().setHeader("ICOMSUSERNAME", credential.getUsername());
		PreferencesSearchRequest  request= exchange.getIn().getBody(PreferencesSearchRequest.class);
		//	String showTypes = (String) context.getHeader().getShow();
		String showTypes="";
		//	icomsSqlRequest = getIcomsSql(exchange);	
		Notifications notifications = new Notifications();
		if (StringUtils.isNotEmpty(showTypes)) {
			StringTokenizer messageTypeTokens = new StringTokenizer(showTypes.trim(), ",");
			while (messageTypeTokens.hasMoreTokens()) {
				String theMsgType = messageTypeTokens.nextToken();
				if (theMsgType.equalsIgnoreCase(L_HeaderFields.NOTIFICATION_METHODS_KEY)) {
					getNotificationMethods(request,notifications, exchange);
				} else if (theMsgType.equalsIgnoreCase(L_HeaderFields.NOTIFICATION_PREFERENCES_KEY)) {
					getNotificationPreferences(request,notifications,exchange);
				} else if (theMsgType.equalsIgnoreCase(L_HeaderFields.UNASSIGNED_MOBILE_NUMBERS_KEY)) {
					getUnassignedSMSMobileNumbers(request,notifications, exchange);
				} else {
					getNotificationMethods(request,notifications, exchange);
					getNotificationPreferences(request,notifications, exchange);
					getUnassignedSMSMobileNumbers(request,notifications, exchange);
				}
			}
		}else{
			getNotificationMethods(request,notifications,exchange);
			getNotificationPreferences(request,notifications, exchange);
			getUnassignedSMSMobileNumbers(request,notifications, exchange);
		}	
		PreferencesSearchResponse preferencesSearchResponse=new PreferencesSearchResponse();
		preferencesSearchResponse.setNotifications(request.getNotifications());
		return preferencesSearchResponse;	
	}	

	private void getNotificationPreferences(PreferencesSearchRequest request, Notifications notifications, Exchange exchange) throws BusinessException, Exception {
		CustomerNotificationRetrievePreferenceRequest CustNotifiRetrievePreRequest = new CustomerNotificationRetrievePreferenceRequest();
		CustNotifiRetrievePreRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
		CustNotifiRetrievePreRequest.setENVNAME(env.getProperty(request.getSiteId()));
		CustNotifiRetrievePreRequest.setSITEID(new Integer(request.getSiteId()));
		CustNotifiRetrievePreRequest.setCUSTNUM(new Integer(request.getAccountNumber()));
		CustNotifiRetrievePreRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());
		CustNotifiRetrievePreRequest.setNUMBEROFRECORDS(new Integer(env.getProperty("customeraccount.customerPreferences.numberofrecord")));


		CustomerNotificationRetrievePreferenceResponse customerNotificationRetrievePreferenceResponse = preferenceDaoImpl.retrieveCustomerNotificationPreference(CustNotifiRetrievePreRequest);
		if (customerNotificationRetrievePreferenceResponse != null) {
			if (!customerNotificationRetrievePreferenceResponse.getReturnCode().trim().equalsIgnoreCase("")) {
				throw new BusinessException(L_ErrorCodes.CUSTOMER_PREFERENCE_SEARCH_FAILED+ " -- " +customerNotificationRetrievePreferenceResponse.getReturnCode()+ " -- " +
						customerNotificationRetrievePreferenceResponse.getMessageText());
			}else{
				List<NotificationPreference> notificationPreferences = new ArrayList<>();
				for (com.cox.bis.customer.preference.icoms.sql.model.NotificationPreference customerNotificationPreferenceDB : customerNotificationRetrievePreferenceResponse
						.getNotificationPreferences()) {
					NotificationPreference notificationPreference = new NotificationPreference();
					if (customerNotificationPreferenceDB.getMethod() != null) {
						notificationPreference.setMethod(customerNotificationPreferenceDB.getMethod().trim());
					}
					if (customerNotificationPreferenceDB.getMethodDescription() != null) {
						notificationPreference.setMethodDescription(customerNotificationPreferenceDB.getMethodDescription().trim());
					}
					if (customerNotificationPreferenceDB.getAddressType() != null) {
						notificationPreference.setAddressType(ValidationHelper.translateAddressType(customerNotificationPreferenceDB.getAddressType().trim().toUpperCase()));
					}
					if (customerNotificationPreferenceDB.getCategory() != null) {
						notificationPreference.setCategory(customerNotificationPreferenceDB.getCategory().trim());
					}
					if (customerNotificationPreferenceDB.getCategoryDescription() != null) {
						notificationPreference.setCategoryDescription(customerNotificationPreferenceDB.getCategoryDescription().trim());
					}
					if (customerNotificationPreferenceDB.getOptIn() != null
							&& customerNotificationPreferenceDB.getOptIn().trim().equalsIgnoreCase("I")) {
						notificationPreference.setOptIn(L_Messages.TRUE_KEY);
					} else {
						notificationPreference.setOptIn(L_Messages.FALSE_KEY);
					}
					if (customerNotificationPreferenceDB.getIsAllowed() != null
							&& customerNotificationPreferenceDB.getIsAllowed().trim().equalsIgnoreCase("Y")) {
						notificationPreference.setIsAllowed(L_Messages.TRUE_KEY);
					} else {
						notificationPreference.setIsAllowed(L_Messages.FALSE_KEY);
					}
					if (customerNotificationPreferenceDB.getIsEditable() != null) {
						if (customerNotificationPreferenceDB.getIsEditable().trim().equalsIgnoreCase("3")
								|| customerNotificationPreferenceDB.getIsEditable().trim().equalsIgnoreCase("1")) {
							notificationPreference.setIsEditable(L_Messages.TRUE_KEY);
						} else
							notificationPreference.setIsEditable(L_Messages.FALSE_KEY);
					} else {
						notificationPreference.setIsEditable(L_Messages.FALSE_KEY);
					}	
					if (customerNotificationPreferenceDB.getCreatedBy() != null) {
						notificationPreference.setCreatedBy(customerNotificationPreferenceDB.getCreatedBy().trim());
					}	
					if (customerNotificationPreferenceDB.getCreatedDate() != null && !customerNotificationPreferenceDB.getCreatedDate().trim().equalsIgnoreCase("0000000")) {
						notificationPreference.setCreatedDate(DateTimeProcessor.fromCenturyDate(customerNotificationPreferenceDB.getCreatedDate().trim()));
					}else {
						notificationPreference.setCreatedDate("00000000");
					}	
					if (customerNotificationPreferenceDB.getCreatedTime() != null) {
						notificationPreference.setCreatedTime(String.format("%06d", Integer.parseInt(customerNotificationPreferenceDB.getCreatedTime().trim())));
					}	
					if (customerNotificationPreferenceDB.getChangedBy() != null) {
						notificationPreference.setChangedBy(customerNotificationPreferenceDB.getChangedBy().trim());
					}	
					if (customerNotificationPreferenceDB.getChangedDate() != null && !customerNotificationPreferenceDB.getChangedDate().trim().equalsIgnoreCase("0000000")) {
						notificationPreference.setChangedDate(DateTimeProcessor.fromCenturyDate(customerNotificationPreferenceDB.getChangedDate().trim()));
					}else {
						notificationPreference.setChangedDate("00000000");
					}	
					if (customerNotificationPreferenceDB.getChangedTime() != null) {
						notificationPreference.setChangedTime(String.format("%06d", Integer.parseInt(customerNotificationPreferenceDB.getChangedTime().trim())));
					}	
					notificationPreferences.add(notificationPreference);
				}
				notifications.setNotificationPreferences(notificationPreferences);
			}
		}else {
			throw new BusinessException("Error Occured while Retrieving Customer Notification Preferences");
		}

	}

	public void getNotificationMethods(PreferencesSearchRequest request, Notifications notifications,Exchange exchange)
			throws BusinessException, Exception {
		CustomerNotificationRetrieveMethodRequest customerNotificationRetrieveMethodRequest = new CustomerNotificationRetrieveMethodRequest();
		customerNotificationRetrieveMethodRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
		customerNotificationRetrieveMethodRequest.setENVNAME(env.getProperty(request.getSiteId()));
		customerNotificationRetrieveMethodRequest.setSITEID(new Integer(request.getSiteId()));
		customerNotificationRetrieveMethodRequest.setCUSTNUM(new Integer(request.getAccountNumber()));
		customerNotificationRetrieveMethodRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());
		customerNotificationRetrieveMethodRequest.setNUMBEROFRECORDS(new Integer(env.getProperty("customeraccount.customerPreferences.numberofrecord")));
		CustomerNotificationRetrieveMethodResponse icomsSqlResponse = preferenceDaoImpl.retrieveCustomerNotificationMethod(customerNotificationRetrieveMethodRequest);
		if (icomsSqlResponse != null) {
			if (!icomsSqlResponse.getReturnCode().trim().equalsIgnoreCase("")) {
				throw new BusinessException(L_ErrorCodes.CUSTOMER_PREFERENCE_SEARCH_FAILED + " -- " + icomsSqlResponse.getReturnCode() + " -- " + 
						icomsSqlResponse.getMessageText());
			}
			else {
				List<NotificationMethod> notificationMethods = new ArrayList<>();
				for (com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod customerNotificationMethodDB : icomsSqlResponse.getNotificationMethods()) {
					NotificationMethod notificationMethod = new NotificationMethod();
					if (customerNotificationMethodDB.getMethod() != null)
						notificationMethod.setMethod(customerNotificationMethodDB.getMethod().trim());
					if (customerNotificationMethodDB.getAddressType() != null)						
						notificationMethod.setAddressType(ValidationHelper.translateAddressType(customerNotificationMethodDB.getAddressType().trim().toUpperCase()));
					if (customerNotificationMethodDB.getAddress() != null)
						if(customerNotificationMethodDB.getMethod().toLowerCase().contains("phone") || customerNotificationMethodDB.getMethod().toLowerCase().contains("sms")){
							notificationMethod.setAddress(customerNotificationMethodDB.getAddress().replaceAll("[\\s\\-()]", "").trim());		
						}else{
							notificationMethod.setAddress(customerNotificationMethodDB.getAddress().trim());
						}
					if (customerNotificationMethodDB.getIsMobile() != null && customerNotificationMethodDB.getIsMobile().trim().equalsIgnoreCase("Y")) {
						notificationMethod.setIsMobile(L_Messages.TRUE_KEY);
					} else {
						notificationMethod.setIsMobile(L_Messages.FALSE_KEY);
					}
					if (customerNotificationMethodDB.getIsPrimary() != null
							&& customerNotificationMethodDB.getIsPrimary().trim().equalsIgnoreCase("Y")) {
						notificationMethod.setIsPrimary(L_Messages.TRUE_KEY);
					} else {
						notificationMethod.setIsPrimary(L_Messages.FALSE_KEY);
					}
					if (customerNotificationMethodDB.getServiceOptIn() != null){
						if(customerNotificationMethodDB.getServiceOptIn().trim().equalsIgnoreCase("I")) {
							notificationMethod.setServiceOptIn(L_Messages.TRUE_KEY);
						}else {
							notificationMethod.setServiceOptIn(L_Messages.FALSE_KEY);						
						} 
					}
					if (customerNotificationMethodDB.getIsEditable() != null
							&& customerNotificationMethodDB.getIsEditable().trim().equalsIgnoreCase("P")) {
						notificationMethod.setIsEditable(L_Messages.FALSE_KEY);
					} else notificationMethod.setIsEditable(L_Messages.TRUE_KEY);

					if (customerNotificationMethodDB.getVerificationStatus() != null) {
						notificationMethod.setVerificationStatus(customerNotificationMethodDB.getVerificationStatus().trim());
					}				
					if (customerNotificationMethodDB.getVerificationStatusDescription() != null)
						notificationMethod.setVerificationStatusDescription(customerNotificationMethodDB.getVerificationStatusDescription().trim());
					if (customerNotificationMethodDB.getVerificationDate() != null && !customerNotificationMethodDB.getVerificationDate().trim().equalsIgnoreCase("0000000")) {
						notificationMethod.setVerificationDate(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.getVerificationDate()));
					} else {
						notificationMethod.setVerificationDate("00000000");
					}
					if (customerNotificationMethodDB.getConfirmStatus() != null
							&& customerNotificationMethodDB.getConfirmStatus().trim().equalsIgnoreCase("Y")) {
						notificationMethod.setConfirmStatus(L_Messages.TRUE_KEY);
					} else {
						notificationMethod.setConfirmStatus(L_Messages.FALSE_KEY);
					}
					if (customerNotificationMethodDB.getConfirmDate() != null
							&& !customerNotificationMethodDB.getConfirmDate().trim().equalsIgnoreCase("0000000")) {
						notificationMethod.setConfirmDate(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.getConfirmDate()));
					} else {
						notificationMethod.setConfirmDate("00000000");
					}
					if (customerNotificationMethodDB.getCreatedBy() != null) {
						notificationMethod.setCreatedBy(customerNotificationMethodDB.getCreatedBy().trim());
					}
					if ((customerNotificationMethodDB.getCreatedDate() != null) && (!customerNotificationMethodDB.getCreatedDate().trim().equalsIgnoreCase("0000000"))) {
						notificationMethod.setCreatedDate(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.getCreatedDate()));
					} else {
						notificationMethod.setCreatedDate("00000000");
					}
					if (customerNotificationMethodDB.getCreatedTime()!= null) {
						notificationMethod.setCreatedTime(String.format("%06d", Integer.parseInt(customerNotificationMethodDB.getCreatedTime())));
					}
					if (customerNotificationMethodDB.getChangedBy() != null) {
						notificationMethod.setChangedBy(customerNotificationMethodDB.getChangedBy().trim());
					}
					if (customerNotificationMethodDB.getChangedDate() != null
							&& !customerNotificationMethodDB.getChangedDate().trim().equalsIgnoreCase("0000000")) {
						notificationMethod.setChangedDate(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.getChangedDate()));
					} else {
						notificationMethod.setChangedDate("00000000");
					}
					if (customerNotificationMethodDB.getChangedTime()!= null) {
						notificationMethod.setChangedTime(String.format("%06d", Integer.parseInt(customerNotificationMethodDB.getChangedTime())));
					}
					if (customerNotificationMethodDB.getCreatedByMobile() != null) {
						notificationMethod.setCreatedByMobile(customerNotificationMethodDB.getCreatedByMobile().trim());
					}
					if (customerNotificationMethodDB.getCreatedDateMobile() != null
							&& !customerNotificationMethodDB.getCreatedDateMobile().trim().equalsIgnoreCase("0000000")) {
						notificationMethod.setCreatedDateMobile(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.getCreatedDateMobile()));
					} else {
						notificationMethod.setCreatedDateMobile("00000000");
					}
					if (customerNotificationMethodDB.getCreatedTimeMobile()!= null) {
						notificationMethod.setCreatedTimeMobile(String.format("%06d", Integer.parseInt(customerNotificationMethodDB.getCreatedTimeMobile())));
					}
					if (customerNotificationMethodDB.getChangedByMobile() != null) {
						notificationMethod.setChangedByMobile(customerNotificationMethodDB.getChangedByMobile().trim());
					}
					if (customerNotificationMethodDB.getChangedDateMobile() != null
							&& !customerNotificationMethodDB.getChangedDateMobile().trim().equalsIgnoreCase("0000000")) {
						notificationMethod.setChangedDateMobile(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.getChangedDateMobile()));
					} else {
						notificationMethod.setChangedDateMobile("00000000");
					}
					if (customerNotificationMethodDB.getChangedTimeMobile()!= null) {
						notificationMethod.setChangedTimeMobile(String.format("%06d", Integer.parseInt(customerNotificationMethodDB.getChangedTimeMobile())));
					}
					notificationMethods.add(notificationMethod);
				}
				notifications.setNotificationMethods(notificationMethods);
				request.setNotifications(notifications);
			}
		} else {
			throw new BusinessException("Error Occured while Retrieving Customer Notification Methods");
		}

	}

	private void getUnassignedSMSMobileNumbers(PreferencesSearchRequest request,Notifications notifications, Exchange exchange) throws BusinessException, Exception{
		CustomerNotificationRetrieveUnassignSMSMethodsRequest notificationRetrieveUnassignSMSRequest = new CustomerNotificationRetrieveUnassignSMSMethodsRequest();
		notificationRetrieveUnassignSMSRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
		notificationRetrieveUnassignSMSRequest.setENVNAME(env.getProperty(request.getSiteId()));
		notificationRetrieveUnassignSMSRequest.setSITEID(new Integer(request.getSiteId()));
		notificationRetrieveUnassignSMSRequest.setCUSTNUM(new Integer(request.getAccountNumber()));
		notificationRetrieveUnassignSMSRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());
		notificationRetrieveUnassignSMSRequest.setNUMBEROFRECORDS(new Integer(env.getProperty("customeraccount.customerPreferences.numberofrecord")));
		CustomerNotificationRetrieveUnassignSMSMethodsResponse icomsSqlResponse = preferenceDaoImpl.
				retrieveUnassignedSMSMethods(notificationRetrieveUnassignSMSRequest);
		if (icomsSqlResponse != null) {
			if (!icomsSqlResponse.getReturnCode().trim().equalsIgnoreCase("")) {
				throw new BusinessException(L_ErrorCodes.CUSTOMER_PREFERENCE_SEARCH_FAILED + " -- " + icomsSqlResponse.getReturnCode() + " -- " + 
						icomsSqlResponse.getMessageText());
			}
			else {
				List<UnassignedSMSMobileNumber> unassignedSMSMobileNumbers = new ArrayList<>();
				for (com.cox.bis.customer.preference.icoms.sql.model.UnassignedSMSMobileNumber unassignedSMSMobileNumberDB : icomsSqlResponse.getUnassignedSMSMobileNumbers()) {
					UnassignedSMSMobileNumber unassignedMobileNumber = new UnassignedSMSMobileNumber();
					if (unassignedSMSMobileNumberDB.getAddressType() != null)						
						unassignedMobileNumber.setAddressType(ValidationHelper.translateAddressType(unassignedSMSMobileNumberDB.getAddressType().trim().toUpperCase()));
					if (unassignedSMSMobileNumberDB.getAddress() != null)
						if(unassignedSMSMobileNumberDB.getAddressType().toLowerCase().contains("hp") || unassignedSMSMobileNumberDB.getAddressType().toLowerCase().contains("bp")  || unassignedSMSMobileNumberDB.getAddressType().toLowerCase().contains("op") ){
							unassignedMobileNumber.setAddress(unassignedSMSMobileNumberDB.getAddress().replaceAll("[\\s\\-()]", "").trim());		
						}else{
							unassignedMobileNumber.setAddress(unassignedSMSMobileNumberDB.getAddress().trim());
						}
					if (unassignedSMSMobileNumberDB.getCreatedBy() != null) {
						unassignedMobileNumber.setCreatedBy(unassignedSMSMobileNumberDB.getCreatedBy().trim());
					}
					if ((unassignedSMSMobileNumberDB.getCreatedDate() != null) && (!unassignedSMSMobileNumberDB.getCreatedDate().trim().equalsIgnoreCase("0000000"))) {
						unassignedMobileNumber.setCreatedDate(DateTimeProcessor.fromCenturyDate(unassignedSMSMobileNumberDB.getCreatedDate()));
					} else {
						unassignedMobileNumber.setCreatedDate("00000000");
					}
					if (unassignedSMSMobileNumberDB.getCreatedTime()!= null) {
						unassignedMobileNumber.setCreatedTime(String.format("%06d", Integer.parseInt(unassignedSMSMobileNumberDB.getCreatedTime())));
					}
					if (unassignedSMSMobileNumberDB.getChangedBy() != null) {
						unassignedMobileNumber.setChangedBy(unassignedSMSMobileNumberDB.getChangedBy().trim());
					}
					if (unassignedSMSMobileNumberDB.getChangedDate() != null
							&& !unassignedSMSMobileNumberDB.getChangedDate().trim().equalsIgnoreCase("0000000")) {
						unassignedMobileNumber.setChangedDate(DateTimeProcessor.fromCenturyDate(unassignedSMSMobileNumberDB.getChangedDate()));
					} else {
						unassignedMobileNumber.setChangedDate("00000000");
					}
					if (unassignedSMSMobileNumberDB.getChangedTime()!= null) {
						unassignedMobileNumber.setChangedTime(String.format("%06d", Integer.parseInt(unassignedSMSMobileNumberDB.getChangedTime())));
					}

					unassignedSMSMobileNumbers.add(unassignedMobileNumber);
				}
				notifications.setUnassignedSMSMobileNumbers(unassignedSMSMobileNumbers);
				request.setNotifications(notifications);

			}
		} else {
			throw new BusinessException( "Error Occured while retrieving Unassigned SMS Mobile Numbers");
		}

	}
}