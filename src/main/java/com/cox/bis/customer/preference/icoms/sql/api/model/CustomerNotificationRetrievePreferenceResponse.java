package com.cox.bis.customer.preference.icoms.sql.api.model;

import java.util.List;

import com.cox.bis.customer.preference.icoms.sql.model.NotificationPreference;

public class CustomerNotificationRetrievePreferenceResponse{

	private String returnCode;
	private String messageText;
	List<NotificationPreference> notificationPreferences;

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

	public List<NotificationPreference> getNotificationPreferences() {
		return notificationPreferences;
	}

	public void setNotificationPreferences(List<NotificationPreference> notificationPreferences) {
		this.notificationPreferences = notificationPreferences;
	}
}
