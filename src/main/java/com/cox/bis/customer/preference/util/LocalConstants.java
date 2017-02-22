package com.cox.bis.customer.preference.util;


public interface LocalConstants{
	// Exception Messages
		final static String NO_HTTP_RESPONSE_EXCEPTION_MESSAGE = "Server failed to respond.  Enable logging on the apache http client library (org.apache.http,org.apache.httpcomponents.httpclient) for further details e.g. log:set DEBUG org.apache.http";
		final static String UNEXPECTED_FAILURE = "Failed to complete operation. Enable logging on the icoms-api bundle (com.cox.bis.icoms.api) for further details.";
		
		
		//Log Messages
		final static String ICOMS_GATEWAY_MESSAGE_HEADER = "\n *** Icoms Gateway Message *** \n";
		final static String HTTP_RESPONSE_CODE = "Http Response Code : ";
		final static String CONTENT_LENGTH = "Content Length : ";
		final static String CONTENT_TYPE = "Content Type : ";
		final static String BREADCRUMB_ID = "Breadcrumb Id : ";
		final static String RESPONSE_MESSAGE = "Message : \n";
		final static String WRAPPED_INPUT_STREAM_CLASS_NAME = "WrappedInputStream";
		
		
		// Constant Variables
		final static String LINE_BREAK = "\n";
		
		
		// Exchange Property Keys
		final static String REQUEST_OBJECT_KEY = "REQUEST_OBJECT";
		final static String LOG_ENTRY_KEY = "LOG_ENTRY";
		final static String OPERATION_NAME = "OPERATION_NAME";
		final static String TRANSACTION_ID_KEY = "TRANSACTION_ID";
		final static String CLIENT_TRANSACTION_ID_KEY = "CLIENT_TRANSACTION_ID";
		final static String SESSION_ID_KEY = "SESSION_ID";
		final static String CLIENT_SESSION_ID_KEY = "CLIENT_SESSION_ID";
		final static String CLIENT_ID_KEY = "CLIENT_ID";
		final static String CALLING_SERVICE_KEY = "CALLING_SERVICE";
		
	public interface L_Operations{
		static final String ACCOUNTS_SEARCH = "accountsSearch";
		static final String ACCOUNTS_GET = "accountsGet";
		static final String COMMENTS_SEARCH = "commentsSearch";
		static final String COMMENTS_CREATE = "commentsCreate";
		static final String COMMENTS_UPDATE = "commentsUpdate";
		static final String COMMENTS_DELETE = "commentsDelete";
		static final String PREFERENCES_SEARCH = "preferencesSearch";
		static final String PREFERENCES_UPDATE = "preferencesUpdate";
	}

	public interface L_Properties{
		static final String ACCOUNT_NUMBER_KEY = "ACCOUNT_NUMBER";
		static final String ACCOUNT_NUMBERS_KEY = "ACCOUNT_NUMBERS";
		static final String PHONE_KEY = "PHONE";
		static final String EMAIL_KEY = "EMAIL";
		static final String FIRST_NAME_KEY = "FIRST_NAME";
		static final String LAST_NAME_KEY = "LAST_NAME";
		static final String ADDRESS_LINE_1_KEY = "ADDRESS_LINE_1";
		static final String ADDRESS_LINE_2_KEY = "ADDRESS_LINE_2";
		static final String UNIT_KEY = "UNIT";
		static final String CITY_KEY = "ADDRESS_CITY";
		static final String STATE_KEY = "ADDRESS_STATE";
		static final String ZIP5_KEY = "ADDRESS_ZIP5";
		static final String DIRECTIVES_KEY = "DIRECTIVES";
		static final String DEEP_SEARCH_KEY = "DEEP_SEARCH";
		static final String SORT = "SORT";
		static final String NOTIFICATION_METHODS_FLAG = "NOTIFICATION_METHODS_FLAG";
		static final String NOTIFICATION_PREFERENCES_FLAG = "NOTIFICATION_PREFERENCES_FLAG";
		static final String UNASSIGNED_MOBILE_NUMBERS_FLAG = "UNASSIGNED_MOBILE_NUMBERS_FLAG";
		public final String YES = "Y";
		public final String NO = "N";
		public final String BLANK = " ";
		public final String OPT_IN = "I";
		public final String OPT_OUT = "O";
		

	}

	public interface L_HeaderFields{
		static final String ACCOUNT_NUMBER_KEY = "accountNumber";
		static final String ACCOUNT_NUMBERS_KEY = "accountNumbers";
		static final String PHONE_KEY = "phone";
		static final String EMAIL_KEY = "email";
		static final String FIRST_NAME_KEY = "firstName";
		static final String LAST_NAME_KEY = "lastName";
		static final String ADDRESS_LINE_1_KEY = "addressLine1";
		static final String ADDRESS_LINE_2_KEY = "addressLine2";
		static final String UNIT_KEY_KEY = "unit";
		static final String CITY_KEY = "city";
		static final String STATE_KEY = "state";
		static final String ZIP5_KEY = "zip5";
		static final String DIRECTIVES_KEY = "directives";
		static final String DEEP_SEARCH_KEY = "deepSearch";
		static final String SORT = "sort";
		static final String  SHOW_KEY = "show";
		static final String  HIDE_KEY = "hide";
		static final String NOTIFICATION_METHODS_KEY = "notificationMethods";
		static final String NOTIFICATION_PREFERENCES_KEY = "notificationPreferences";
		static final String UNASSIGNED_MOBILE_NUMBERS_KEY = "unassignedSMSMobileNumbers";
		public final String SHOW_LIST = "notificationMethods,notificationPreferences,unassignedMobileNumbers";
	}

	public interface L_LogFields{
		static final String ACCOUNT_NUMBER_KEY = "accountNumber";
		static final String ACCOUNT_NUMBERS_KEY = "accountNumbers";
		static final String PHONE_KEY = "phone";
		static final String EMAIL_KEY = "email";
		static final String FIRST_NAME_KEY = "firstName";
		static final String LAST_NAME_KEY = "lastName";
		static final String ADDRESS_LINE_1_KEY = "addressLine1";
		static final String ADDRESS_LINE_2_KEY = "addressLine2";
		static final String UNIT_KEY_KEY = "unit";
		static final String CITY_KEY = "city";
		static final String STATE_KEY = "state";
		static final String ZIP5_KEY = "zip5";
		static final String DIRECTIVES_KEY = "directives";
		static final String DEEP_SEARCH_KEY = "deepSearch";
	}

	public interface L_MessageCodes{
		static final String STEP_NETCRACKER_SP_CALL = "STEP_NETCRACKER_SP_CALL";		
		static final String STEP_CUSTOMER_PREFERENCE_UPDATE = "STEP_CUSTOMER_PREFERENCE_UPDATE";		
		static final String STEP_CUSTOMER_PREFERENCE_SEARCH = "STEP_CUSTOMER_PREFERENCE_SEARCH";		
		
	}

	public interface L_Messages{
		static final String TRUE_KEY="true";
		static final String FALSE_KEY="false";
		static final String BLANK_KEY=" ";
		static final String STEP_FAILED="STEP_FAILED";
		static final String STEP_SUCCESS="STEP_SUCCESS";
	}

	public interface L_ErrorCodes{
		static final String CUSTOMER_COMMENTS_SEARCH_FAILED = "CUSTOMER_COMMENTS_SEARCH_FAILED";
		static final String CUSTOMER_COMMENTS_CREATE_FAILED = "CUSTOMER_COMMENTS_ADD_FAILED";
		static final String CUSTOMER_COMMENTS_UPDATE_FAILED = "CUSTOMER_COMMENTS_UPDATE_FAILED";
		static final String CUSTOMER_COMMENTS_DELETE_FAILED = "CUSTOMER_COMMENTS_DELETE_FAILED";
		static final String CUSTOMER_PREFERENCE_SEARCH_FAILED="CUSTOMER_PREFERENCE_SEARCH_FAILED";
		static final String ICOMS_SQL_API_FAILURE ="ICOMS_SQL_API_FAILURE";
	}

	public interface L_Errors {
		static final String CUSTOMER_COMMENTS_SEARCH_FAILED = "Search Customer CommentsProcessor Failed";
		static final String CUSTOMER_COMMENTS_CREATE_FAILED = "Create Customer CommentsProcessor Failed";
		static final String CUSTOMER_COMMENTS_UPDATE_FAILED = "Update Customer CommentsProcessor Failed";
		static final String CUSTOMER_COMMENTS_DELETE_FAILED = "Delete Customer CommentsProcessor Failed";
		static final String CUSTOMER_PREFERENCE_SEARCH_FAILED = "Search Customer Preference Failed";
	}
	
	public interface C_ErrorCodes {
		static final String BUSINESS_SERVICE_FAILURE = "BUSINESS_SERVICE_FAILURE";
		static final String UNEXPECTED_FAILURE = "UNEXPECTED_FAILURE";
		static final String NON_FAILURE_EXCEPTION  = "NON_FAILURE_EXCEPTION";
		@Deprecated
		static final String FAILURE_TRANSACTION = "FAILURE_TRANSACTION";
		@Deprecated
		static final String GENERAL_FAILURE = "GENERAL_FAILURE"; // Use UNEXPECTED_FAILURE
		@Deprecated
		static final String GENERAL_ERROR = "GENERAL_FAILURE"; // Use UNEXPECTED_FAILURE
		@Deprecated
		static final String UNCAUGHT_EXCEPTION = "UNCAUGHT_EXCEPTION"; // Use UNEXPECTED_FAILURE
		@Deprecated
		static final String DB_GENERAL_ERROR = "DB_GENERAL_ERROR"; // Use UNEXPECTED_DB_FAILURE
		static final String UNEXPECTED_DB_FAILURE = "UNEXPECTED_DB_FAILURE";
		@Deprecated
		static final String ICOMS_API = "ICOMS_API_ERROR"; // Use ICOMS_API_FAILURE
		static final String ICOMS_API_FAILURE = "ICOMS_API_FAILURE";
		static final String ICOMS_NO_CONNECTION_ALIAS_FOUND = "ICOMS_NO_CONNECTION_ALIAS_FOUND";
		static final String NO_DATA_FOUND = "NO_DATA_FOUND";
		static final String NO_RECORDS_FOUND = "NO_RECORDS_FOUND";
		static final String OPERATION_NOT_YET_AVAILABLE = "OPERATION_NOT_YET_AVAILABLE";
		static final String OPERATION_DISABLED = "OPERATION_DISABLED";
		static final String REQUIRED_FIELDS_MISSING = "REQUIRED_FIELDS_MISSING";
		static final String REQUIRED_BODY_MISSING = "REQUIRED_BODY_MISSING";
		static final String VALIDATION_FAILURE = "VALIDATION_FAILURE";
		static final String INVALID_SITE_ID = "INVALID_SITE_ID";
		static final String INVALID_ACCOUNT_NUMBER = "INVALID_ACCOUNT_NUMBER";
		static final String INVALID_HOUSE_NUMBER = "INVALID_HOUSE_NUMBER";
		static final String INVALID_COMPANY_DIVISION = "INVALID_COMPANY_DIVISION";
		static final String INVALID_CLIENT_IDENTIFIER = "INVALID_CLIENT_IDENTIFIER";
		static final String INVALID_DATE_FORMAT = "INVALID_DATE_FORMAT";
		static final String INVALID_PIN = "INVALID_PIN";
		static final String INVALID_SESSION_ID = "INVALID_SESSION_ID";
		static final String WM_INVALID_SITE_ID = "200001";
		static final String WM_INVALID_CLIENT_IDENTIFIER = "200000";
		static final String WM_CLIENT_IDENTIFIER_SERVICE_INVOKE = "200003";
		static final String WM_INVALID_DATE_FORMAT = "100004";
		static final String WM_NO_DATA_FOUND = "100005";
		static final String WM_NO_CONNECTION_ALIAS_FOUND = "200011";
		static final String WM_INVALID_ACCOUNT_NUMBER = "100000";
	}

	public interface L_Formats {
	}
	
	public interface AddressScrubConstants {
			static final String SOAPACTION = "SOAPAction";
			static final String ERROR_MSG_STATUS="Exception";
			static final String MSG_DELIMETER = "::";
	}
	
	public interface IcomsSqlConstants {
		
		static final String UPDATE_SHIPMENTS_SP_NAME = "COXGPL.CPE_VENDOR_RETURN";
		static final String UPDATE_INVENTORY_SP_NAME = "COXGPL.CPE_INVENTORY_API";
		static final String TRACK_BILLING_ADDRESS_SP_NAME = "COXGPL.TRACK_ADDRESS_EXT";
		static final String SAVE_CPNI_PROFILE_SP_NAME = "COXGPL.SAVE_CPNI_EXTERNAL";
		static final String GET_BYPASS_REASONCODE_SP_NAME = "COXGPL.GET_BYPASS_CODES_EXT";
		static final String LOG_CPNI_AUTHENTICATION_SP_NAME = "COXGPL.LOG_AUTHENTICATE_EXT";
		static final String VALIDATE_DROP_SHIP_SP_NAME = "COXGPL.VALIDATE_DROPSHIP";
		static final String VALIDATE_ICOMS_USER_SP_NAME = "COXGPL.CSS5000NSP";
		static final String RETRIEVE_CUST_NOT_METHOD_SP_NAME = "RTVCUSMTHE";
		static final String RETRIEVE_CUST_NOT_PREFERENCES_SP_NAME = "RTVCUSPRFE";
		static final String UPDATE_CUST_NOT_PREFERENCES_SP_NAME = "MNTCUSPRFE";
		static final String UPDATE_CUST_NOT_METHOD_SP_NAME = "MNTCUSMTHE";
		static final String RETRIEVE_CUST_UNASSIGNED_SMS_SP_NAME = "RTVUNASMBE";
		static final String UPDATE_CUST_NOT_METHOD_ADDRESS_TYPE_SP_NAME = "MNTADRTYPE";
		static final String ICONNECT_ERROR_MESSAGE = "Transaction not processed (IConnect Manager Error)";
		static final String DATE_PATTERN = "yyyyMMdd'T'HHmmss.SSS";

	}
}
