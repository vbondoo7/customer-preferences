package com.cox.bis.customer.preference.api.model;

/**
 * 
 */


import java.io.Serializable;

import org.apache.camel.Exchange;

import com.cox.bis.customer.preference.model.Notifications;


// TODO: Auto-generated Javadoc
/**
 * The Class PreferencesSearchResponse.
 *
 * @author Kumar Saurav
 */
public class PreferencesSearchResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 86511070066381519L;

	/** The notifications. */
	private Notifications notifications;

	/**
	 * Instantiates a new customer preferences search response.
	 */
	public PreferencesSearchResponse() {

	}

	/**
	 * Instantiates a new customer preferences search response.
	 *
	 * @param context the context
	 */
	
	/**
	 * Instantiates a new customer preferences search response.
	 *
	 * @param context the context
	 * @param code the code
	 * @param message the message
	 */
	public PreferencesSearchResponse(Exchange exchange, String code, String message) {
		setNotifications(((PreferencesSearchResponse) exchange).getNotifications());
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
