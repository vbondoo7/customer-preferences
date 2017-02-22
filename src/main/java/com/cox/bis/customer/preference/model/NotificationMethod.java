package com.cox.bis.customer.preference.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.StringUtils;

/**
 * The Class NotificationMethod.
 *
 * @author Suresh Rimmalapudi
 */
public class NotificationMethod implements Comparable<NotificationMethod>{
	
	/** The method. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String method;
	
	/** The address type. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String addressType;
	
	/** The address. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String address;
	
	/** The is mobile. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String isMobile;
	
	/** The is primary. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String isPrimary;
	
	/** The service opt in. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String serviceOptIn;
	
	/** The is editable. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String isEditable;
	
	/** The verification status. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String verificationStatus;
	
	/** The verification status description. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String verificationStatusDescription;
	
	/** The verification date. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String verificationDate;
	
	/** The confirm status. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String confirmStatus;
	
	/** The confirm date. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String confirmDate;
	
	/** The confirm time. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String confirmTime;
	
	/** The changed by. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedBy;
	
	/** The changed date. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedDate;
	
	/** The changed time. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedTime;
	
	/** The created by. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdBy;
	
	/** The created date. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdDate;
	
	/** The created time. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdTime;
	
	/** The changed by mobile. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedByMobile;
	
	/** The changed date mobile. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedDateMobile;
	
	/** The changed time mobile. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedTimeMobile;
	
	/** The created by mobile. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdByMobile;
	
	/** The created date mobile. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdDateMobile;
	
	/** The created time mobile. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String createdTimeMobile;	
	
	/** The success. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String success;
	
	/** The message. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Message message;


	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the method.
	 *
	 * @param method            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Gets the address type.
	 *
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}

	/**
	 * Sets the address type.
	 *
	 * @param addressType            the addressType to set
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
	 * @param address            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the checks if is mobile.
	 *
	 * @return the isMobile
	 */
	public String getIsMobile() {
		return isMobile;
	}

	/**
	 * Sets the checks if is mobile.
	 *
	 * @param isMobile            the isMobile to set
	 */
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

	/**
	 * Gets the checks if is primary.
	 *
	 * @return the isPrimary
	 */
	public String getIsPrimary() {
		return isPrimary;
	}

	/**
	 * Sets the checks if is primary.
	 *
	 * @param isPrimary            the isPrimary to set
	 */
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	/**
	 * Gets the service opt in.
	 *
	 * @return the serviceOptIn
	 */
	public String getServiceOptIn() {
		return serviceOptIn;
	}

	/**
	 * Sets the service opt in.
	 *
	 * @param serviceOptIn            the serviceOptIn to set
	 */
	public void setServiceOptIn(String serviceOptIn) {
		this.serviceOptIn = serviceOptIn;
	}

	/**
	 * Gets the checks if is editable.
	 *
	 * @return the isEditable
	 */
	public String getIsEditable() {
		return isEditable;
	}

	/**
	 * Sets the checks if is editable.
	 *
	 * @param isEditable            the isEditable to set
	 */
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * Gets the verification status.
	 *
	 * @return the verificationStatus
	 */
	public String getVerificationStatus() {
		return verificationStatus;
	}

	/**
	 * Sets the verification status.
	 *
	 * @param verificationStatus            the verificationStatus to set
	 */
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	/**
	 * Gets the verification status description.
	 *
	 * @return the verificationStatusDescription
	 */
	public String getVerificationStatusDescription() {
		return verificationStatusDescription;
	}

	/**
	 * Sets the verification status description.
	 *
	 * @param verificationStatusDescription            the verificationStatusDescription to set
	 */
	public void setVerificationStatusDescription(String verificationStatusDescription) {
		this.verificationStatusDescription = verificationStatusDescription;
	}

	/**
	 * Gets the verification date.
	 *
	 * @return the verificationDate
	 */
	public String getVerificationDate() {
		return verificationDate;
	}

	/**
	 * Sets the verification date.
	 *
	 * @param verificationDate            the verificationDate to set
	 */
	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}

	/**
	 * Gets the confirm status.
	 *
	 * @return the confirmStatus
	 */
	public String getConfirmStatus() {
		return confirmStatus;
	}

	/**
	 * Sets the confirm status.
	 *
	 * @param confirmStatus            the confirmStatus to set
	 */
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	/**
	 * Gets the confirm date.
	 *
	 * @return the confirmDate
	 */
	public String getConfirmDate() {
		return confirmDate;
	}

	/**
	 * Sets the confirm date.
	 *
	 * @param confirmDate            the confirmDate to set
	 */
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	 * Gets the confirm time.
	 *
	 * @return the confirm time
	 */
	public String getConfirmTime() {
		return confirmTime;
	}

	/**
	 * Sets the confirm time.
	 *
	 * @param confirmTime the new confirm time
	 */
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
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
	 * Gets the changed by mobile.
	 *
	 * @return the changed by mobile
	 */
	public String getChangedByMobile() {
		return changedByMobile;
	}

	/**
	 * Sets the changed by mobile.
	 *
	 * @param changedByMobile the new changed by mobile
	 */
	public void setChangedByMobile(String changedByMobile) {
		this.changedByMobile = changedByMobile;
	}

	/**
	 * Gets the changed date mobile.
	 *
	 * @return the changed date mobile
	 */
	public String getChangedDateMobile() {
		return changedDateMobile;
	}

	/**
	 * Sets the changed date mobile.
	 *
	 * @param changedDateMobile the new changed date mobile
	 */
	public void setChangedDateMobile(String changedDateMobile) {
		this.changedDateMobile = changedDateMobile;
	}

	/**
	 * Gets the changed time mobile.
	 *
	 * @return the changed time mobile
	 */
	public String getChangedTimeMobile() {
		return changedTimeMobile;
	}

	/**
	 * Sets the changed time mobile.
	 *
	 * @param changedTimeMobile the new changed time mobile
	 */
	public void setChangedTimeMobile(String changedTimeMobile) {
		this.changedTimeMobile = changedTimeMobile;
	}

	/**
	 * Gets the created by mobile.
	 *
	 * @return the created by mobile
	 */
	public String getCreatedByMobile() {
		return createdByMobile;
	}

	/**
	 * Sets the created by mobile.
	 *
	 * @param createdByMobile the new created by mobile
	 */
	public void setCreatedByMobile(String createdByMobile) {
		this.createdByMobile = createdByMobile;
	}

	/**
	 * Gets the created date mobile.
	 *
	 * @return the created date mobile
	 */
	public String getCreatedDateMobile() {
		return createdDateMobile;
	}

	/**
	 * Sets the created date mobile.
	 *
	 * @param createdDateMobile the new created date mobile
	 */
	public void setCreatedDateMobile(String createdDateMobile) {
		this.createdDateMobile = createdDateMobile;
	}

	/**
	 * Gets the created time mobile.
	 *
	 * @return the created time mobile
	 */
	public String getCreatedTimeMobile() {
		return createdTimeMobile;
	}

	/**
	 * Sets the created time mobile.
	 *
	 * @param createdTimeMobile the new created time mobile
	 */
	public void setCreatedTimeMobile(String createdTimeMobile) {
		this.createdTimeMobile = createdTimeMobile;
	}

	@Override
	public int compareTo(NotificationMethod compareto) {		
		boolean isPrimaryValue =  StringUtils.equalsIgnoreCase(this.isPrimary, "true");		
		if(isPrimaryValue == true)
			return -1;
		else return 1;
		
	}

	@Override
	public String toString() {
		return "NotificationMethod [method=" + method + ", addressType="
				+ addressType + ", address=" + address + ", serviceOptIn="
				+ serviceOptIn + ", isPrimary="+isPrimary + "]";
	}
}