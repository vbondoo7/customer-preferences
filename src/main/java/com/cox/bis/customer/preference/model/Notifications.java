package com.cox.bis.customer.preference.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class Notifications.
 */
public class Notifications {

	/** The notification methods. */
	private List<NotificationMethod> notificationMethods;
	
	/** The notification preferences. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<NotificationPreference> notificationPreferences;
	
	/** The unassigned SMS mobile numbers. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<UnassignedSMSMobileNumber> unassignedSMSMobileNumbers;

	/**
	 * Gets the notification methods.
	 *
	 * @return the notification methods
	 */
	public List<NotificationMethod> getNotificationMethods() {
		if (notificationMethods == null)
			return new ArrayList<NotificationMethod>();
		return notificationMethods;
	}

	/**
	 * Sets the notification methods.
	 *
	 * @param notificationMethods the new notification methods
	 */
	public void setNotificationMethods(List<NotificationMethod> notificationMethods) {
		this.notificationMethods = notificationMethods;
	}

	/**
	 * Gets the notification preferences.
	 *
	 * @return the notification preferences
	 */
	public List<NotificationPreference> getNotificationPreferences() {
		if (notificationPreferences == null)
			return new ArrayList<NotificationPreference>();
		return notificationPreferences;
	}

	/**
	 * Sets the notification preferences.
	 *
	 * @param notificationPreferences the new notification preferences
	 */
	public void setNotificationPreferences(List<NotificationPreference> notificationPreferences) {
		this.notificationPreferences = notificationPreferences;
	}

	/**
	 * Gets the unassigned SMS mobile numbers.
	 *
	 * @return the unassigned SMS mobile numbers
	 */
	public List<UnassignedSMSMobileNumber> getUnassignedSMSMobileNumbers() {
		if (unassignedSMSMobileNumbers == null)
			return new ArrayList<UnassignedSMSMobileNumber>();
		return unassignedSMSMobileNumbers;
	}

	/**
	 * Sets the unassigned SMS mobile numbers.
	 *
	 * @param unassignedSMSMobileNumbers the new unassigned SMS mobile numbers
	 */
	public void setUnassignedSMSMobileNumbers(List<UnassignedSMSMobileNumber> unassignedSMSMobileNumbers) {
		this.unassignedSMSMobileNumbers = unassignedSMSMobileNumbers;
	}
}
