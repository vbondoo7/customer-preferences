package com.cox.bis.customer.preference.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Class UnassignedSMSMobileNumber.
 *
 * @author Kumar Saurav
 */
public class UnassignedSMSMobileNumber {
	
	/** The address type. */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String addressType;
	
	/** The address. */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String address;
	
	/** The created by. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdBy;
	
	/** The created date. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdDate;
	
	/** The created time. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdTime;
	
	/** The changed by. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedBy;
	
	/** The changed date. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedDate;
	
	/** The changed time. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedTime;
	
	/** The success. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String success;
	
	/** The message. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Message message;
	
	/**
	 * Gets the address type.
	 *
	 * @return the address type
	 */
	public String getAddressType() {
		return addressType;
	}
	
	/**
	 * Sets the address type.
	 *
	 * @param addressType the new address type
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the created time.
	 *
	 * @return the created time
	 */
	public String getCreatedTime() {
		return createdTime;
	}
	
	/**
	 * Sets the created time.
	 *
	 * @param createdTime the new created time
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	
	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	
	/**
	 * Gets the changed by.
	 *
	 * @return the changed by
	 */
	public String getChangedBy() {
		return changedBy;
	}
	
	/**
	 * Sets the changed by.
	 *
	 * @param changedBy the new changed by
	 */
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	
	/**
	 * Gets the changed date.
	 *
	 * @return the changed date
	 */
	public String getChangedDate() {
		return changedDate;
	}
	
	/**
	 * Sets the changed date.
	 *
	 * @param changedDate the new changed date
	 */
	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}
	
	/**
	 * Gets the changed time.
	 *
	 * @return the changed time
	 */
	public String getChangedTime() {
		return changedTime;
	}
	
	/**
	 * Sets the changed time.
	 *
	 * @param changedTime the new changed time
	 */
	public void setChangedTime(String changedTime) {
		this.changedTime = changedTime;
	}
	

}
