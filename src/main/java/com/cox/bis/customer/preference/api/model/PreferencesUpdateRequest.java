/**
 * 
 */
package com.cox.bis.customer.preference.api.model;

import java.io.Serializable;

import com.cox.bis.customer.preference.model.Notifications;

// TODO: Auto-generated Javadoc
/**
 * The Class PreferencesUpdateRequest.
 *
 * @author Suresh Rimmalapudi
 */
public class PreferencesUpdateRequest implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2119500880737955467L;

	/** The site id. */
	private String siteId;

	/** The account number. */
	private String accountNumber;

	/** The notifications. */
	private Notifications notifications;

	/**
	 * Instantiates a new customer preferences update request.
	 */
	public PreferencesUpdateRequest() {
	}


	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public Notifications getNotifications() {
		if (notifications == null)
			return new Notifications();
		return notifications;
	}

	/**
	 * Sets the notifications.
	 *
	 * @param notifications
	 *            the new notifications
	 */
	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}

	/**
	 * Gets the site id.
	 *
	 * @return the site id
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * Sets the site id.
	 *
	 * @param siteId
	 *            the new site id
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber
	 *            the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
