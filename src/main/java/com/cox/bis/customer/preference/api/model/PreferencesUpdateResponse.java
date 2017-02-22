/**
 * 
 */
package com.cox.bis.customer.preference.api.model;

import java.io.Serializable;

import com.cox.bis.customer.preference.model.Notifications;

// TODO: Auto-generated Javadoc
/**
 * The Class PreferencesUpdateResponse.
 *
 * @author Suresh Rimmalapudi
 */
public class PreferencesUpdateResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2614803882951933690L;
	/** The notifications. */
	private Notifications notifications;

	/**
	 * Instantiates a new customer preferences update response.
	 */
	public PreferencesUpdateResponse() {

	}

	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public Notifications getNotifications() {
		return notifications;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications the new notifications
	 */
	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}

	
}
