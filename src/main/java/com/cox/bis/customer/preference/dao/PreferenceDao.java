package com.cox.bis.customer.preference.dao;

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


public interface PreferenceDao {

	public Credential icomsCredential(String clientId);
	public CustomerNotificationRetrieveMethodResponse retrieveCustomerNotificationMethod(
			CustomerNotificationRetrieveMethodRequest request) throws Exception;
	public CustomerNotificationUpdateMethodResponse updateCustomerNotificationMethod(
			CustomerNotificationUpdateMethodRequest request, IcomsSqlContext context) throws Exception;
	public CustomerNotificationUpdatePreferenceResponse updateCustomerNotificationPreference(
			CustomerNotificationUpdatePreferenceRequest request, IcomsSqlContext context) throws Exception;
	public CustomerNotificationUpdateAddressTypeResponse updateCustomerMethodAddressType(
			CustomerNotificationUpdateAddressTypeRequest request, IcomsSqlContext context) throws Exception;
	public CustomerNotificationRetrievePreferenceResponse retrieveCustomerNotificationPreference(
			CustomerNotificationRetrievePreferenceRequest request) throws Exception;
	public CustomerNotificationRetrieveUnassignSMSMethodsResponse retrieveUnassignedSMSMethods(CustomerNotificationRetrieveUnassignSMSMethodsRequest request ) throws Exception;

}
