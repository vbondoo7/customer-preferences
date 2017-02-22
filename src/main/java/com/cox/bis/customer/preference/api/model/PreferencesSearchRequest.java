package com.cox.bis.customer.preference.api.model;

/**
 * 
 */


import java.io.Serializable;

import com.cox.bis.customer.preference.model.Notifications;


// TODO: Auto-generated Javadoc
/**
 * The Class PreferencesSearchRequest.
 *
 * @author Kumar Saurav
 */
public class PreferencesSearchRequest implements Serializable {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 926483646663033748L;
	
	/** The site id. */
	
	private String siteId;
	
	/** The account number. */
	private String accountNumber;
	private Notifications notifications;

	
	
	public Notifications getNotifications() {
		return notifications;
	}

	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}

	/**
	 * Instantiates a new customer preferences search request.
	 */
	public PreferencesSearchRequest() {
	}

	/**
	 * Instantiates a new customer preferences search request.
	 *
	 * @param context the context
	 */
	

	/**
	 * Gets the account number.
	 *
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}


	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	/**
	 * Gets the site id.
	 *
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}


	/**
	 * Sets the site id.
	 *
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}


	}
