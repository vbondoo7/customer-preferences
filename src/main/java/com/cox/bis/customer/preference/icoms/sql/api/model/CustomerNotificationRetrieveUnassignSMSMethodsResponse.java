package com.cox.bis.customer.preference.icoms.sql.api.model;

import java.util.List;

import com.cox.bis.customer.preference.icoms.sql.model.UnassignedSMSMobileNumber;


public class CustomerNotificationRetrieveUnassignSMSMethodsResponse {

	private String returnCode;
	private String messageText;
	List<UnassignedSMSMobileNumber> unassignedSMSMobileNumbers;

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

	public List<UnassignedSMSMobileNumber> getUnassignedSMSMobileNumbers() {
		return unassignedSMSMobileNumbers;
	}

	public void setUnassignedSMSMobileNumbers(List<UnassignedSMSMobileNumber> unassignedSMSMobileNumbers) {
		this.unassignedSMSMobileNumbers = unassignedSMSMobileNumbers;
	}

}