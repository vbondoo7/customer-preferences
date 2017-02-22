package com.cox.bis.customer.preference.bean;

import java.util.Collections;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cox.bis.customer.preference.api.model.PreferencesUpdateRequest;
import com.cox.bis.customer.preference.api.model.PreferencesUpdateResponse;
import com.cox.bis.customer.preference.dao.impl.PreferenceDaoImpl;
import com.cox.bis.customer.preference.exception.BusinessException;
import com.cox.bis.customer.preference.exception.InvalidDateFormatException;
import com.cox.bis.customer.preference.icoms.bean.Credential;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveMethodRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationRetrieveMethodResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateAddressTypeRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateAddressTypeResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateMethodRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdateMethodResponse;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdatePreferenceRequest;
import com.cox.bis.customer.preference.icoms.sql.api.model.CustomerNotificationUpdatePreferenceResponse;
import com.cox.bis.customer.preference.icoms.sql.model.IcomsSqlContext;
import com.cox.bis.customer.preference.model.Message;
import com.cox.bis.customer.preference.model.NotificationMethod;
import com.cox.bis.customer.preference.model.NotificationPreference;
import com.cox.bis.customer.preference.util.LocalConstants;
import com.cox.bis.customer.preference.util.Util;
import com.cox.bis.customer.preference.util.ValidationHelper;

/**
 * The Class PreferencesUpdateProcessor provisions customer notification methods
 * and preferences updates.
 *
 * @author Suresh Rimmalapudi
 */
@Component
public class PreferencesUpdateProcessor implements LocalConstants {

	@Autowired
	private Environment env;

	@Autowired
	private PreferenceDaoImpl preferenceDaoImpl;

	public void validate(Exchange exchange)	throws BusinessException, Exception {
		PreferencesUpdateRequest preferencesUpdateRequest =  exchange.getIn().getBody(PreferencesUpdateRequest.class);

		if (CollectionUtils.isEmpty(preferencesUpdateRequest.getNotifications().getNotificationMethods()) && CollectionUtils.isEmpty(preferencesUpdateRequest.getNotifications().getNotificationPreferences())) {
			throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "please check your input, either notificationMethods or notificationPreferences should be present");
		} else {
			//validate notification methods
			if (!CollectionUtils.isEmpty(preferencesUpdateRequest.getNotifications().getNotificationMethods())) {
				for (NotificationMethod notificationMethod : preferencesUpdateRequest.getNotifications().getNotificationMethods()) {					
					if (Util.isNullEmpty(notificationMethod.getAddress())) {
						throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "notificationMethod.address is a required field");
					} else if(notificationMethod.getAddress().trim().length() > 80){
						throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationMethod.address max length is 80");
					}
					if (Util.isNullEmpty(notificationMethod.getAddressType())) {
						throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "notificationMethod.addressType is a required field");
					}
					if (Util.isNullEmpty(notificationMethod.getMethod())) {
						throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "notificationMethod.method is a required field");
					}
					//if address is clear, set all other fields to blank
					if("clear".equalsIgnoreCase(notificationMethod.getAddress().trim())){
						//set clear flag in context
						// context.setClearAddress(true);
						//set remaining fields to blank
						notificationMethod.setAddress(L_Messages.BLANK_KEY);
						notificationMethod.setIsMobile(L_Messages.BLANK_KEY);
						notificationMethod.setIsPrimary(L_Messages.BLANK_KEY);
						notificationMethod.setServiceOptIn(L_Messages.BLANK_KEY);
						notificationMethod.setVerificationStatus(L_Messages.BLANK_KEY);
						notificationMethod.setConfirmStatus(L_Messages.BLANK_KEY);

					}else{
						//validate isMobile Flag
						if (Util.isNotNullEmpty(notificationMethod.getIsMobile())) {
							if (!notificationMethod.getIsMobile().trim().equalsIgnoreCase("true") && !notificationMethod.getIsMobile().trim().equalsIgnoreCase("false") ) {
								throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationMethod.isMobile valid values are 'true' and 'false' ");
							}
						} else {
							notificationMethod.setIsMobile(L_Messages.BLANK_KEY);
						}
						//validate primary flag
						if (Util.isNotNullEmpty(notificationMethod.getIsPrimary())) {
							if (!notificationMethod.getIsPrimary().trim().equalsIgnoreCase("true") && !notificationMethod.getIsPrimary().trim().equalsIgnoreCase("false")) {
								throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationMethod.isPrimary valid values are 'true' and 'false' ");
							}
						} else  {
							notificationMethod.setIsPrimary(L_Messages.BLANK_KEY);
						}
						//validate method
						if (notificationMethod.getMethod().trim().equalsIgnoreCase("sms")) {
							if (Util.isNullEmpty(notificationMethod.getServiceOptIn())) {
								throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "notificationMethod.serviceOptIn is a required field for SMS method");
							} else if (!notificationMethod.getServiceOptIn().trim().equalsIgnoreCase("true") && !notificationMethod.getServiceOptIn().trim().equalsIgnoreCase("false")) {
								throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationMethod.serviceOptIn valid values are 'true' and 'false' ");
							}
						}else {
							if (Util.isNotNullEmpty(notificationMethod.getServiceOptIn())) {
								if (!notificationMethod.getServiceOptIn().trim().equalsIgnoreCase("true") && !notificationMethod.getServiceOptIn().trim().equalsIgnoreCase("false")) {
									throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationMethod.serviceOptIn valid values are 'true' and 'false' ");
								}
							} else {
								notificationMethod.setServiceOptIn(L_Messages.BLANK_KEY);
							}
						}
						//validate verification status	
						if (Util.isNotNullEmpty(notificationMethod.getVerificationStatus())) {
							if (!StringUtils.contains("012345", notificationMethod.getVerificationStatus())) {
								throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationMethod.verificationStatus valid values are 0,1,2,3,4,5");
							}
						} else  {
							notificationMethod.setVerificationStatus(L_Messages.BLANK_KEY);
						}

						//validate confirm status
						if (Util.isNotNullEmpty(notificationMethod.getConfirmStatus())) {
							if (!notificationMethod.getConfirmStatus().trim().equalsIgnoreCase("true") && !notificationMethod.getConfirmStatus().trim().equalsIgnoreCase("false")) {
								throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationMethod.confirmStatus valid values are 'true' and 'false' ");
							}
						} else{
							notificationMethod.setConfirmStatus(L_Messages.BLANK_KEY);
						}

					}
				}			
			}
			//validate notification preferences
			if (!CollectionUtils.isEmpty(preferencesUpdateRequest.getNotifications().getNotificationPreferences())) {
				for (NotificationPreference notificationPreference : preferencesUpdateRequest.getNotifications().getNotificationPreferences()) {
					if (Util.isNullEmpty(notificationPreference.getCategory())) {
						throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "notificationPreference.category is a required field");
					}
					if (Util.isNullEmpty(notificationPreference.getMethod())) {
						throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "notificationPreference.method is a required field");
					}
					if (Util.isNullEmpty(notificationPreference.getOptIn())) {
						throw new BusinessException(C_ErrorCodes.REQUIRED_FIELDS_MISSING + " -- " + "notificationPreference.optIn is a required field");
					} else {
						if (!notificationPreference.getOptIn().trim().equalsIgnoreCase("true") && !notificationPreference.getOptIn().trim().equalsIgnoreCase("false")) {
							throw new BusinessException(C_ErrorCodes.VALIDATION_FAILURE + " -- " + "notificationPreference.optIn valid values are 'true' and 'false' ");
						}
					}
				}
			}
		}
		exchange.getIn().setBody(preferencesUpdateRequest);
	}

	public void init(Exchange exchange)	throws BusinessException, Exception {
		PreferencesUpdateRequest preferencesUpdateRequest =  exchange.getIn().getBody(PreferencesUpdateRequest.class);
		for (NotificationMethod notificationMethod : preferencesUpdateRequest.getNotifications().getNotificationMethods()) {
			if (StringUtils.equalsIgnoreCase(notificationMethod.getMethod().trim(), "sms")) {
				NotificationMethod smsNotificationMethod = new NotificationMethod();
				smsNotificationMethod.setMethod(notificationMethod.getMethod());
				smsNotificationMethod.setAddressType(notificationMethod.getAddressType());
				smsNotificationMethod.setAddress(notificationMethod.getAddress());
				smsNotificationMethod.setIsMobile(notificationMethod.getIsMobile());
				smsNotificationMethod.setIsPrimary(notificationMethod.getIsPrimary());
				smsNotificationMethod.setServiceOptIn(notificationMethod.getServiceOptIn());
				smsNotificationMethod.setVerificationStatus(notificationMethod.getVerificationStatus());
				smsNotificationMethod.setConfirmStatus(notificationMethod.getConfirmStatus());
				//context.setSmsNotificationMethod(smsNotificationMethod);
				exchange.getIn().setHeader("SMSNOTIFICATIONMETHOD", smsNotificationMethod);
				preferencesUpdateRequest.getNotifications().getNotificationMethods().remove(notificationMethod);
				break;
			}
		}
	}

	private List<com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod> retrieveExistingMethodState(Exchange exchange, CustomerNotificationRetrieveMethodRequest retrieveExistingNotificationMethodsRequest) throws Exception {
		// get existing Notification Method Address Type from DB
		//IcomsSqlContext icomsSqlContext = new IcomsSqlContext("customerPreferencesUpdate","","","","");
		CustomerNotificationRetrieveMethodResponse retrieveExistingNotificationMethodsResponse = preferenceDaoImpl.retrieveCustomerNotificationMethod(retrieveExistingNotificationMethodsRequest);

		if (retrieveExistingNotificationMethodsResponse != null) {
			if (!retrieveExistingNotificationMethodsResponse.getReturnCode().trim().equalsIgnoreCase("")) {
				throw new BusinessException(L_ErrorCodes.ICOMS_SQL_API_FAILURE + " -- " +
						retrieveExistingNotificationMethodsResponse.getMessageText());
			}
		}
		return retrieveExistingNotificationMethodsResponse.getNotificationMethods();
	}

	private boolean optOutSMSNotificationMethod(Exchange exchange, List<com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod> customerNotificationPreferenceDB1, PreferencesUpdateRequest preferencesUpdateRequest) throws Exception {
		NotificationMethod smsNotificationMethod = (NotificationMethod)exchange.getIn().getHeader("SMSNOTIFICATIONMETHOD");
		//PreferencesUpdateRequest preferencesUpdateRequest =  exchange.getIn().getBody(PreferencesUpdateRequest.class);
		boolean isOptOptOut = true;
		for (com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod notificationMethodDB : customerNotificationPreferenceDB1) {
			isOptOptOut = false;
			if (notificationMethodDB.getMethod() != null && notificationMethodDB.getMethod().equalsIgnoreCase("sms")) {
				if (StringUtils.equalsIgnoreCase(notificationMethodDB.getServiceOptIn().trim(), "I")) {
					if ((smsNotificationMethod!=null && !(StringUtils.equalsIgnoreCase(notificationMethodDB.getAddressType().trim(), ValidationHelper.translateAddressType(smsNotificationMethod.getAddressType().trim().toUpperCase()))))) {
						CustomerNotificationUpdateMethodRequest icomsSqlRequest = new CustomerNotificationUpdateMethodRequest();
						icomsSqlRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
						icomsSqlRequest.setENVNAME(env.getProperty(preferencesUpdateRequest.getSiteId()));
						icomsSqlRequest.setSITEID(Integer.valueOf(preferencesUpdateRequest.getSiteId()));
						icomsSqlRequest.setCUSTNUM(Integer.valueOf(preferencesUpdateRequest.getAccountNumber()));
						icomsSqlRequest.setNOTFMETHOD(notificationMethodDB.getMethod());
						icomsSqlRequest.setNOTFADDR(notificationMethodDB.getAddressType().toUpperCase());
						icomsSqlRequest.setADDRESS(notificationMethodDB.getAddress());						
						icomsSqlRequest.setMOBILE(L_Properties.YES);					
						icomsSqlRequest.setPRIMARY(L_Properties.YES);
						// OPT OUT SMS notification method
						icomsSqlRequest.setNOTFOPTN(L_Properties.OPT_OUT);
						icomsSqlRequest.setVERIFYSTS(notificationMethodDB.getVerificationStatus());
						icomsSqlRequest.setCONFIRMSTS("*");
						icomsSqlRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());

						// Initiate ICOMS Response
						CustomerNotificationUpdateMethodResponse icomsSqlResponseUpdateMethod = null;

						// call ICOMS SP too OPT OUT SMS notification method
						IcomsSqlContext icomsSqlContext = new IcomsSqlContext("customerPreferencesUpdate","","","","");
						icomsSqlResponseUpdateMethod = preferenceDaoImpl.updateCustomerNotificationMethod(icomsSqlRequest, icomsSqlContext);
						//null out obsolete references
						//icomsSqlRequest = null;
						if (icomsSqlResponseUpdateMethod != null) {
							if (!icomsSqlResponseUpdateMethod.getReturnCode().trim().equalsIgnoreCase("")) {
								throw new BusinessException(L_ErrorCodes.ICOMS_SQL_API_FAILURE+ " -- " +icomsSqlResponseUpdateMethod.getMessageText());
							}else {
								isOptOptOut = true;
							}
						}
					} else {
						isOptOptOut = true;
					}

				}else {
					isOptOptOut = true;
				}
			}else {
				isOptOptOut = true;
			}

		}

		return isOptOptOut;
	}

	private NotificationMethod buildNotificationMethodsResultSet(NotificationMethod notificationMethod, List<com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod> customerNotificationMethodDB)
			throws InvalidDateFormatException {
		notificationMethod.setSuccess("true");
		if (customerNotificationMethodDB.get(0).getMethod() != null) {
			notificationMethod.setMethod(customerNotificationMethodDB.get(0).getMethod().trim());
		}
		if (customerNotificationMethodDB.get(0).getAddressType() != null) {
			notificationMethod.setAddressType(ValidationHelper.translateAddressType(customerNotificationMethodDB.get(0).getAddressType().trim()));
		}
		if (customerNotificationMethodDB.get(0).getAddress() != null) {
			if (customerNotificationMethodDB.get(0).getMethod().toLowerCase().contains("phone")	|| customerNotificationMethodDB.get(0).getMethod().toLowerCase().contains("sms")) {
				notificationMethod.setAddress(customerNotificationMethodDB.get(0).getAddress().replaceAll("[\\s\\-()]", "").trim());
			} else {
				notificationMethod.setAddress(customerNotificationMethodDB.get(0).getAddress().trim());
			}
		}
		if (customerNotificationMethodDB.get(0).getIsMobile() != null
				&& customerNotificationMethodDB.get(0).getIsMobile().trim().equalsIgnoreCase("Y")) {
			notificationMethod.setIsMobile(L_Messages.TRUE_KEY);
		} else {
			notificationMethod.setIsMobile(L_Messages.FALSE_KEY);
		}
		if (customerNotificationMethodDB.get(0).getIsPrimary() != null
				&& customerNotificationMethodDB.get(0).getIsPrimary().trim().equalsIgnoreCase("Y")) {
			notificationMethod.setIsPrimary(L_Messages.TRUE_KEY);
		} else {
			notificationMethod.setIsPrimary(L_Messages.FALSE_KEY);
		}
		if (customerNotificationMethodDB.get(0).getServiceOptIn() != null
				&& customerNotificationMethodDB.get(0).getServiceOptIn().trim().equalsIgnoreCase("I")) {
			notificationMethod.setServiceOptIn(L_Messages.TRUE_KEY);
		} else {
			notificationMethod.setServiceOptIn(L_Messages.FALSE_KEY);
		}
		if (customerNotificationMethodDB.get(0).getVerificationStatus() != null) {
			notificationMethod.setVerificationStatus(customerNotificationMethodDB.get(0).getVerificationStatus().trim());
		}
		if (customerNotificationMethodDB.get(0).getVerificationDate() != null) {
			notificationMethod.setConfirmDate(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.get(0).getConfirmDate().trim()));
		}
		if (customerNotificationMethodDB.get(0).getVerificationTime() != null) {
			notificationMethod.setConfirmTime(String.format("%06d",	Integer.parseInt(customerNotificationMethodDB.get(0).getVerificationTime().trim())));
		}
		if (customerNotificationMethodDB.get(0).getConfirmStatus() != null && customerNotificationMethodDB.get(0).getConfirmStatus().trim().equalsIgnoreCase("Y")) {
			notificationMethod.setConfirmStatus(L_Messages.TRUE_KEY);
		} else {
			notificationMethod.setConfirmStatus(L_Messages.FALSE_KEY);
		}
		if (customerNotificationMethodDB.get(0).getConfirmDate() != null) {
			notificationMethod.setConfirmDate(String.format( DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.get(0).getConfirmDate().trim())));
		}
		if (customerNotificationMethodDB.get(0).getConfirmTime() != null) {
			notificationMethod.setConfirmTime(String.format("%06d",
					Integer.parseInt(customerNotificationMethodDB.get(0).getConfirmTime().trim())));
		}
		if (customerNotificationMethodDB.get(0).getCreatedBy() != null) {
			notificationMethod.setCreatedBy(customerNotificationMethodDB.get(0).getCreatedBy().trim());
		}
		if (customerNotificationMethodDB.get(0).getCreatedDate() != null) {
			notificationMethod.setCreatedDate(	DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.get(0).getCreatedDate().trim()));
		}
		if (customerNotificationMethodDB.get(0).getCreatedTime() != null) {
			notificationMethod.setCreatedTime(String.format("%06d",	Integer.parseInt(customerNotificationMethodDB.get(0).getCreatedTime().trim())));
		}
		if (customerNotificationMethodDB.get(0).getChangedBy() != null) {
			notificationMethod.setChangedBy(customerNotificationMethodDB.get(0).getChangedBy().trim());
		}
		if (customerNotificationMethodDB.get(0).getChangedDate() != null) {
			notificationMethod.setChangedDate(DateTimeProcessor.fromCenturyDate(customerNotificationMethodDB.get(0).getChangedDate().trim()));
		}
		if (customerNotificationMethodDB.get(0).getChangedTime() != null) {
			notificationMethod.setChangedTime(String.format("%06d",	Integer.parseInt(customerNotificationMethodDB.get(0).getChangedTime().trim())));
		}
		//null out obsolete references
		//customerNotificationMethodDB = null;
		return notificationMethod;
	}

	private void updateNotificationMethods(Exchange exchange, PreferencesUpdateRequest preferencesUpdateRequest){
		//sort notification methods
		IcomsSqlContext icomsSqlContext = new IcomsSqlContext("customerPreferencesUpdate","","","","");
		//Credential credential = preferenceDaoImpl.icomsCredential("test");
		if(Util.isNotNullEmpty(preferencesUpdateRequest.getNotifications().getNotificationMethods())){
			Collections.sort(preferencesUpdateRequest.getNotifications().getNotificationMethods());
			//loop over sorted notification methods
			for (NotificationMethod notificationMethod : preferencesUpdateRequest.getNotifications().getNotificationMethods()) {

				CustomerNotificationUpdateMethodRequest icomsSqlRequest = new CustomerNotificationUpdateMethodRequest();
				if (!StringUtils.equalsIgnoreCase(notificationMethod.getMethod().trim(), "sms") && !StringUtils.equalsIgnoreCase(notificationMethod.getMethod().trim(), null)) {
					try {
						icomsSqlRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
						icomsSqlRequest.setENVNAME(env.getProperty(preferencesUpdateRequest.getSiteId()));
						icomsSqlRequest.setSITEID(Integer.valueOf(preferencesUpdateRequest.getSiteId()));
						icomsSqlRequest.setCUSTNUM(Integer.valueOf(preferencesUpdateRequest.getAccountNumber()));
						icomsSqlRequest.setNOTFMETHOD(notificationMethod.getMethod());
						icomsSqlRequest.setNOTFADDR(ValidationHelper.translateAddressType(notificationMethod.getAddressType().toUpperCase()));
						icomsSqlRequest.setADDRESS(notificationMethod.getAddress());
						if (notificationMethod.getIsMobile().trim().equals(L_Messages.TRUE_KEY)) {
							icomsSqlRequest.setMOBILE(L_Properties.YES);
						} else if (notificationMethod.getIsMobile().trim().equals(L_Messages.FALSE_KEY)) {
							icomsSqlRequest.setMOBILE(L_Properties.NO);
						} else {
							icomsSqlRequest.setMOBILE(notificationMethod.getIsMobile());
						}
						if (notificationMethod.getIsPrimary().trim().equals(L_Messages.TRUE_KEY)) {
							icomsSqlRequest.setPRIMARY(L_Properties.YES);
						} else if (notificationMethod.getIsPrimary().trim().equals(L_Messages.FALSE_KEY)) {
							icomsSqlRequest.setPRIMARY(L_Properties.NO);
						} else {
							icomsSqlRequest.setPRIMARY(notificationMethod.getIsPrimary());
						}
						if (notificationMethod.getServiceOptIn().trim().equals(L_Messages.TRUE_KEY)) {
							icomsSqlRequest.setNOTFOPTN(L_Properties.OPT_IN);
						} else if (notificationMethod.getServiceOptIn().trim().equals(L_Messages.FALSE_KEY)) {
							icomsSqlRequest.setNOTFOPTN(L_Properties.OPT_OUT);
						} else {
							icomsSqlRequest.setNOTFOPTN(notificationMethod.getServiceOptIn());
						}
						icomsSqlRequest.setVERIFYSTS(notificationMethod.getVerificationStatus());
						if (notificationMethod.getConfirmStatus().trim().equals(L_Messages.TRUE_KEY)) {
							icomsSqlRequest.setCONFIRMSTS("*");
						} else if (notificationMethod.getConfirmStatus().trim().equals(L_Messages.FALSE_KEY)) {
							icomsSqlRequest.setCONFIRMSTS(L_Properties.NO);
						} else {
							icomsSqlRequest.setCONFIRMSTS(notificationMethod.getConfirmStatus());
						}
						icomsSqlRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());
						//Initiate ICOMS Response
						CustomerNotificationUpdateMethodResponse icomsSqlResponseUpdateMethod = null;	
						CustomerNotificationUpdateMethodResponse icomsSqlResponseSecondUpdateMethod = null;

						//call ICOMS SP
						icomsSqlResponseUpdateMethod = preferenceDaoImpl.updateCustomerNotificationMethod(icomsSqlRequest, icomsSqlContext);

						if (icomsSqlResponseUpdateMethod != null) {
							if (!icomsSqlResponseUpdateMethod.getReturnCode().trim().equalsIgnoreCase("")) {
								notificationMethod.setSuccess("false");
								throw new BusinessException(L_ErrorCodes.ICOMS_SQL_API_FAILURE + " -- " + icomsSqlResponseUpdateMethod.getMessageText());
							}else if(!notificationMethod.getAddress().equals(" ")){
								//second stage commit
								icomsSqlResponseSecondUpdateMethod = preferenceDaoImpl.updateCustomerNotificationMethod(icomsSqlRequest, icomsSqlContext);
								icomsSqlRequest = null;
								if (!icomsSqlResponseSecondUpdateMethod.getReturnCode().trim().equalsIgnoreCase("")) {
									notificationMethod.setSuccess("false");
									throw new BusinessException(L_ErrorCodes.ICOMS_SQL_API_FAILURE + " -- " + icomsSqlResponseSecondUpdateMethod.getMessageText());
								}
							}

							//chose correct response for result set
							if(icomsSqlResponseSecondUpdateMethod != null){
								List<com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod> customerNotificationMethodDB = icomsSqlResponseSecondUpdateMethod.getNotificationMethods();
								notificationMethod = buildNotificationMethodsResultSet(notificationMethod,customerNotificationMethodDB);							
							}else if(icomsSqlResponseUpdateMethod != null){
								notificationMethod.setSuccess("true");					
							}
						}
					} catch (Exception e) {
						Message message = new Message();
						//  context.setResponseStatus(false);
						exchange.getIn().setHeader("RESPONSESTATUS", false);
						if (e instanceof BusinessException) {
							message.setCode(L_ErrorCodes.ICOMS_SQL_API_FAILURE);
							message.setMessage(e.getMessage());
							notificationMethod.setMessage(message);
						} else {
							message.setCode(C_ErrorCodes.UNEXPECTED_FAILURE);
							message.setMessage(e.toString());
							notificationMethod.setMessage(message);
						}
					}

				}

			}
		}
	}
	
	private NotificationPreference buildNotificationPreferencesResultSet(NotificationPreference notificationPreference,	List<com.cox.bis.customer.preference.icoms.sql.model.NotificationPreference> customerNotificationPreferenceDB)	throws InvalidDateFormatException {
		notificationPreference.setSuccess("true");
		if (customerNotificationPreferenceDB.get(0).getMethod() != null) {
			notificationPreference.setMethod(customerNotificationPreferenceDB.get(0).getMethod().trim());
		}
		if (customerNotificationPreferenceDB.get(0).getAddressType() != null) {
			notificationPreference.setAddressType(customerNotificationPreferenceDB.get(0).getAddressType().trim());
		}
		if (customerNotificationPreferenceDB.get(0).getCategory() != null) {
			notificationPreference.setCategory(customerNotificationPreferenceDB.get(0).getCategory().trim());
		}
		if (customerNotificationPreferenceDB.get(0).getOptIn() != null && customerNotificationPreferenceDB.get(0).getOptIn().trim().equalsIgnoreCase("I")) {
			notificationPreference.setOptIn(L_Messages.TRUE_KEY);
		} else {
			notificationPreference.setOptIn(L_Messages.FALSE_KEY);
		}
		if (customerNotificationPreferenceDB.get(0).getIsAllowed() != null && customerNotificationPreferenceDB.get(0).getIsAllowed().trim().equalsIgnoreCase("Y")) {
			notificationPreference.setIsAllowed(L_Messages.TRUE_KEY);
		} else {
			notificationPreference.setIsAllowed(L_Messages.FALSE_KEY);
		}
		if (customerNotificationPreferenceDB.get(0).getIsEditable() != null) {
			if (customerNotificationPreferenceDB.get(0).getIsEditable().trim().equalsIgnoreCase("3") && customerNotificationPreferenceDB.get(0).getIsEditable().trim().equalsIgnoreCase("1")) {
				notificationPreference.setIsEditable(L_Messages.TRUE_KEY);
			} else {
				notificationPreference.setIsEditable(L_Messages.FALSE_KEY);
			}
		} else {
			notificationPreference.setIsEditable(L_Messages.FALSE_KEY);
		}

		if (customerNotificationPreferenceDB.get(0).getCreatedBy() != null && customerNotificationPreferenceDB.get(0).getCreatedBy() != L_Messages.BLANK_KEY) {
			notificationPreference.setCreatedBy(customerNotificationPreferenceDB.get(0).getCreatedBy().trim());
		}
		if (customerNotificationPreferenceDB.get(0).getCreatedDate() != null && customerNotificationPreferenceDB.get(0).getCreatedDate() != L_Messages.BLANK_KEY) {
			notificationPreference.setCreatedDate(DateTimeProcessor.fromCenturyDate(customerNotificationPreferenceDB.get(0).getCreatedDate().trim()));
		}
		if (customerNotificationPreferenceDB.get(0).getCreatedTime() != null && customerNotificationPreferenceDB.get(0).getCreatedTime() != L_Messages.BLANK_KEY) {
			notificationPreference.setCreatedTime(String.format("%06d", Integer.parseInt(customerNotificationPreferenceDB.get(0).getCreatedTime().trim())));
		}
		if (customerNotificationPreferenceDB.get(0).getChangedBy() != null && customerNotificationPreferenceDB.get(0).getChangedBy() != L_Messages.BLANK_KEY) {
			notificationPreference.setChangedBy(customerNotificationPreferenceDB.get(0).getChangedBy().trim());
		}
		if (customerNotificationPreferenceDB.get(0).getChangedDate() != null && customerNotificationPreferenceDB.get(0).getChangedDate() != L_Messages.BLANK_KEY) {
			notificationPreference.setChangedDate( DateTimeProcessor.fromCenturyDate(customerNotificationPreferenceDB.get(0).getChangedDate().trim()));
		}
		if (customerNotificationPreferenceDB.get(0).getChangedTime() != null && customerNotificationPreferenceDB.get(0).getChangedTime() != L_Messages.BLANK_KEY) {
			notificationPreference.setChangedTime(String.format("%06d", Integer.parseInt(customerNotificationPreferenceDB.get(0).getChangedTime().trim())));
		}
		//null out obsolete references
		//customerNotificationPreferenceDB = null;
		return notificationPreference;
	}
	
	private void updateNotificationPreferences(Exchange exchange, PreferencesUpdateRequest preferencesUpdateRequest){
		
		IcomsSqlContext icomsSqlContext = new IcomsSqlContext("customerPreferencesUpdate","","","","");
		CustomerNotificationUpdatePreferenceRequest icomsSqlRequest = new CustomerNotificationUpdatePreferenceRequest();
		//Credential credential = preferenceDaoImpl.icomsCredential("test");
		for (NotificationPreference notificationPreference : preferencesUpdateRequest.getNotifications().getNotificationPreferences()) {
			
			try {
				icomsSqlRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
				icomsSqlRequest.setENVNAME(env.getProperty(preferencesUpdateRequest.getSiteId()));
				icomsSqlRequest.setSITEID(Integer.valueOf(preferencesUpdateRequest.getSiteId()));
				icomsSqlRequest.setCUSTNUM(Integer.valueOf(preferencesUpdateRequest.getAccountNumber()));
				icomsSqlRequest.setNOTFCATG(notificationPreference.getCategory());
				icomsSqlRequest.setNOTFMETH(notificationPreference.getMethod());
				if (notificationPreference.getOptIn().trim().equals(L_Messages.TRUE_KEY)) {
					icomsSqlRequest.setNOTFOPTN(L_Properties.OPT_IN);
				} else if (notificationPreference.getOptIn().trim().equals(L_Messages.FALSE_KEY)) {
					icomsSqlRequest.setNOTFOPTN(L_Properties.OPT_OUT);
				} else {
					icomsSqlRequest.setNOTFOPTN(L_Messages.BLANK_KEY);
				}
				icomsSqlRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());

				CustomerNotificationUpdatePreferenceResponse icomsSqlResponse = preferenceDaoImpl.updateCustomerNotificationPreference(icomsSqlRequest, icomsSqlContext);

				if (icomsSqlResponse != null) {
					if (!icomsSqlResponse.getReturnCode().trim().equalsIgnoreCase("")) {
						notificationPreference.setSuccess("false");
						throw new BusinessException(L_ErrorCodes.ICOMS_SQL_API_FAILURE + " -- " +
								icomsSqlResponse.getMessageText());

					}

					List<com.cox.bis.customer.preference.icoms.sql.model.NotificationPreference> customerNotificationPreferenceDB = icomsSqlResponse
							.getNotificationPreferences();
					notificationPreference = buildNotificationPreferencesResultSet(notificationPreference,
							customerNotificationPreferenceDB);
				}
			} catch (Exception e) {
				// context.setResponseStatus(false);
				exchange.getIn().setHeader("RESPONSESTATUS", false);
				Message message = new Message();
				if (e instanceof BusinessException) {
					message.setCode(L_ErrorCodes.ICOMS_SQL_API_FAILURE);
					message.setMessage(e.getMessage());
					notificationPreference.setMessage(message);
				} else {
					message.setCode(C_ErrorCodes.UNEXPECTED_FAILURE);
					message.setMessage(e.toString());
					notificationPreference.setMessage(message);
				}
			}
		}
		
		// return context;

	}
	
	private void updateCustomerMethodAddressType(Exchange exchange, List<com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod> customerNotificationPreferenceDB1, PreferencesUpdateRequest preferencesUpdateRequest) {
		
		IcomsSqlContext icomsSqlContext = new IcomsSqlContext("customerPreferencesUpdate","","","","");
		CustomerNotificationUpdateAddressTypeRequest updateSMSNotificationMethod = new CustomerNotificationUpdateAddressTypeRequest();
		CustomerNotificationUpdateMethodRequest updateNotificationMethodRequest = new CustomerNotificationUpdateMethodRequest();
		NotificationMethod notificationMethod = new NotificationMethod();
		NotificationMethod smsNotificationMethod = (NotificationMethod)exchange.getIn().getHeader("SMSNOTIFICATIONMETHOD");
		//Credential credential = preferenceDaoImpl.icomsCredential("test");
		
		try {
			// build request to update SMS method
			updateSMSNotificationMethod.setAPPNAME(env.getProperty("icoms.sql.application.name"));
			updateSMSNotificationMethod.setENVNAME(env.getProperty(preferencesUpdateRequest.getSiteId()));
			updateSMSNotificationMethod.setSITEID(Integer.valueOf(preferencesUpdateRequest.getSiteId()));
			updateSMSNotificationMethod.setCUSTNUM(Integer.valueOf(preferencesUpdateRequest.getAccountNumber()));

			// build request to update SMS notification method flags
			updateNotificationMethodRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
			updateNotificationMethodRequest.setENVNAME(env.getProperty(preferencesUpdateRequest.getSiteId()));
			updateNotificationMethodRequest.setSITEID(Integer.valueOf(preferencesUpdateRequest.getSiteId()));
			updateNotificationMethodRequest.setCUSTNUM(Integer.valueOf(preferencesUpdateRequest.getAccountNumber()));
			updateNotificationMethodRequest.setADDRESS(smsNotificationMethod.getAddress());
			updateNotificationMethodRequest.setNOTFADDR(
					ValidationHelper.translateAddressType(smsNotificationMethod.getAddressType().toUpperCase()));
			if (smsNotificationMethod.getConfirmStatus().trim().equalsIgnoreCase(L_Messages.TRUE_KEY)) {
				updateNotificationMethodRequest.setCONFIRMSTS("*");
			} else if (smsNotificationMethod.getConfirmStatus().trim().equalsIgnoreCase(L_Messages.FALSE_KEY)) {
				updateNotificationMethodRequest.setCONFIRMSTS(L_Properties.NO);
			} else {
				updateNotificationMethodRequest.setCONFIRMSTS(L_Messages.BLANK_KEY);
			}
			updateNotificationMethodRequest.setNOTFMETHOD(smsNotificationMethod.getMethod().trim());
			if (smsNotificationMethod.getServiceOptIn().trim().equals(L_Messages.TRUE_KEY)) {
				updateNotificationMethodRequest.setNOTFOPTN(L_Properties.OPT_IN);
			} else if (smsNotificationMethod.getServiceOptIn().trim().equals(L_Messages.FALSE_KEY)) {
				updateNotificationMethodRequest.setNOTFOPTN(L_Properties.OPT_OUT);
			} else {
				updateNotificationMethodRequest.setNOTFOPTN(L_Messages.BLANK_KEY);
			}
			updateNotificationMethodRequest.setVERIFYSTS(smsNotificationMethod.getVerificationStatus().trim());
			if (smsNotificationMethod.getIsPrimary().trim().equals(L_Messages.TRUE_KEY)) {
				updateNotificationMethodRequest.setPRIMARY(L_Properties.YES);
			} else if (smsNotificationMethod.getIsPrimary().trim().equals(L_Messages.FALSE_KEY)) {
				updateNotificationMethodRequest.setPRIMARY(L_Properties.NO);
			} else {
				updateNotificationMethodRequest.setPRIMARY(L_Messages.BLANK_KEY);
			}
			if (smsNotificationMethod.getIsMobile().trim().equals(L_Messages.TRUE_KEY)) {
				updateNotificationMethodRequest.setMOBILE(L_Properties.YES);
			} else if (smsNotificationMethod.getIsMobile().trim().equals(L_Messages.FALSE_KEY)) {
				updateNotificationMethodRequest.setMOBILE(L_Properties.NO);
			} else {
				updateNotificationMethodRequest.setMOBILE(L_Messages.BLANK_KEY);
			}
			updateNotificationMethodRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());

				for (com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod notificationMethodDB : customerNotificationPreferenceDB1) {
					if (notificationMethodDB.getMethod() != null
							&& notificationMethodDB.getMethod().trim().equalsIgnoreCase("sms")) {
						updateSMSNotificationMethod.setNOTFADDR1(notificationMethodDB.getAddressType().trim());
					} else {
						updateSMSNotificationMethod.setNOTFADDR1(L_Messages.BLANK_KEY);
					}
				}

			updateSMSNotificationMethod.setNOTFMETHOD(smsNotificationMethod.getMethod());
			updateSMSNotificationMethod.setNOTFADDR2(ValidationHelper.translateAddressType(smsNotificationMethod.getAddressType().toUpperCase()));
			updateSMSNotificationMethod.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());

			// update customer method address type from addr1 to addr2
			CustomerNotificationUpdateAddressTypeResponse updateCustomerMethodAddressTypeResponse = preferenceDaoImpl.updateCustomerMethodAddressType(updateSMSNotificationMethod, icomsSqlContext);
			//null out obsolete references
			//updateSMSNotificationMethod = null;
			if (updateCustomerMethodAddressTypeResponse != null) {
				if (!updateCustomerMethodAddressTypeResponse.getReturnCode().trim().equalsIgnoreCase("")) {
					notificationMethod.setSuccess("false");
					throw new BusinessException(L_ErrorCodes.ICOMS_SQL_API_FAILURE +" -- "+
							updateCustomerMethodAddressTypeResponse.getMessageText());

				} else {

					// 2nd stage commit for update customer method address type
					// to update serviceOptIn, verificationStatus, confirmStatus
					// etc
					CustomerNotificationUpdateMethodResponse updateCustomerNotificationMethodResponse = preferenceDaoImpl.updateCustomerNotificationMethod(updateNotificationMethodRequest, icomsSqlContext);
					//null out obsolete references
					//updateNotificationMethodRequest = null;
					if (updateCustomerNotificationMethodResponse != null) {
						if (!updateCustomerNotificationMethodResponse.getReturnCode().trim().equalsIgnoreCase("")) {
							notificationMethod.setSuccess("false");
							throw new BusinessException(L_ErrorCodes.ICOMS_SQL_API_FAILURE + " -- " +
									updateCustomerNotificationMethodResponse.getMessageText());

						} else {
							List<com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod> customerNotificationMethodDB = updateCustomerNotificationMethodResponse
									.getNotificationMethods();
							notificationMethod = buildNotificationMethodsResultSet(notificationMethod,
									customerNotificationMethodDB);
							preferencesUpdateRequest.getNotifications().getNotificationMethods().add(notificationMethod);
						}
					}
				}
			}

		} catch (Exception e) {
			// context.setResponseStatus(false);
			exchange.getIn().setHeader("RESPONSESTATUS", false);
			Message message = new Message();
			if (e instanceof BusinessException) {
				message.setCode(L_ErrorCodes.ICOMS_SQL_API_FAILURE);
				message.setMessage(e.getMessage());
				notificationMethod.setMessage(message);
			} else {
				message.setCode(C_ErrorCodes.UNEXPECTED_FAILURE);
				message.setMessage(e.toString());
				notificationMethod.setMessage(message);

			}
			preferencesUpdateRequest.getNotifications().getNotificationMethods().add(notificationMethod);
		}
		//return context;
	}


	public PreferencesUpdateResponse update(Exchange exchange) throws BusinessException, Exception {

		CustomerNotificationRetrieveMethodRequest retrieveExistingNotificationMethodsRequest = new CustomerNotificationRetrieveMethodRequest();
		PreferencesUpdateRequest preferencesUpdateRequest =  exchange.getIn().getBody(PreferencesUpdateRequest.class);
		NotificationMethod smsNotificationMethod = (NotificationMethod)exchange.getIn().getHeader("SMSNOTIFICATIONMETHOD");
		Credential credential = preferenceDaoImpl.icomsCredential("ibilluser");
		exchange.getIn().setHeader("ICOMSUSERNAME", credential.getUsername());
		
		// build request to retrieve existing SMS address type
		retrieveExistingNotificationMethodsRequest.setAPPNAME(env.getProperty("icoms.sql.application.name"));
		retrieveExistingNotificationMethodsRequest.setENVNAME(env.getProperty(preferencesUpdateRequest.getSiteId()));
		retrieveExistingNotificationMethodsRequest.setSITEID(Integer.valueOf(preferencesUpdateRequest.getSiteId()));
		retrieveExistingNotificationMethodsRequest.setCUSTNUM(Integer.valueOf(preferencesUpdateRequest.getAccountNumber()));
		retrieveExistingNotificationMethodsRequest.setSWAPUSERID(exchange.getIn().getHeader("ICOMSUSERNAME").toString());
		retrieveExistingNotificationMethodsRequest.setNUMBEROFRECORDS(Integer.valueOf(env.getProperty("customeraccount.customerPreferences.numberofrecord")));
		// existing notification methods
		List<com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod> existingNotificationMethods = retrieveExistingMethodState(exchange, retrieveExistingNotificationMethodsRequest);
		// find existing SMS method and OPT OUT if conditions are met
		boolean smsMethodOptedOutFlag = optOutSMSNotificationMethod(exchange, existingNotificationMethods, preferencesUpdateRequest);		

		if(smsMethodOptedOutFlag){

			if (preferencesUpdateRequest.getNotifications().getNotificationMethods() != null) {
				updateNotificationMethods(exchange, preferencesUpdateRequest);
			}
			if (preferencesUpdateRequest.getNotifications().getNotificationPreferences() != null) {
				updateNotificationPreferences(exchange, preferencesUpdateRequest);
			}		
			if (smsNotificationMethod != null) {
				updateCustomerMethodAddressType(exchange, existingNotificationMethods, preferencesUpdateRequest);
			}
		}
		PreferencesUpdateResponse response = new PreferencesUpdateResponse();
		response.setNotifications(preferencesUpdateRequest.getNotifications());
		//null out obsolete references
		retrieveExistingNotificationMethodsRequest = null;

		return response;
	}
}