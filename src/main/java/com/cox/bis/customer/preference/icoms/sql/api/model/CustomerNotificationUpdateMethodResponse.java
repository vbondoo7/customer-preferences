package com.cox.bis.customer.preference.icoms.sql.api.model;

import java.util.List;

import com.cox.bis.customer.preference.icoms.sql.model.NotificationMethod;

/**
 * @author Kumar Saurav
 *
 * 
 */
public class CustomerNotificationUpdateMethodResponse extends OldIcomsSqlResponse {

	private String returnCode;
	private String messageText;
	List<NotificationMethod> notificationMethods;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public List<NotificationMethod> getNotificationMethods() {
		return notificationMethods;
	}

	public void setNotificationMethods(List<NotificationMethod> notificationMethods) {
		this.notificationMethods = notificationMethods;
	}

}