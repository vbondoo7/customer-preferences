package com.cox.bis.customer.preference.model;

import com.fasterxml.jackson.annotation.JsonInclude;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationPreference.
 *
 * @author Kumar Saurav
 */
public class NotificationPreference{

	/** The method. */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String method;
	
	/** The method description. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String methodDescription;
	
	/** The category. */
	@JsonInclude(JsonInclude.Include.ALWAYS)
	private String category;
	
	/** The category description. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String categoryDescription;
	
	/** The address type. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String addressType;
	
	/** The opt in. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String optIn;
	
	/** The is editable. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String isEditable;
	
	/** The is allowed. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String isAllowed;
	
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
	
	/** The changed time. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedTime;
	
	/** The changed date. */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String changedDate;
	
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the method description.
	 *
	 * @return the method description
	 */
	public String getMethodDescription() {
		return methodDescription;
	}

	/**
	 * Sets the method description.
	 *
	 * @param methodDescription the new method description
	 */
	public void setMethodDescription(String methodDescription) {
		this.methodDescription = methodDescription;
	}

	/**
	 * Gets the category description.
	 *
	 * @return the category description
	 */
	public String getCategoryDescription() {
		return categoryDescription;
	}

	/**
	 * Sets the category description.
	 *
	 * @param categoryDescription the new category description
	 */
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	/**
	 * Gets the address type.
	 *
	 * @return the addressType
	 */
	public String getAddressType() {
		if (addressType == null)
			return addressType = "";
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
	 * Gets the opt in.
	 *
	 * @return the optIn
	 */
	public String getOptIn() {
		if (optIn == null)
			optIn = "";
		return optIn;
	}

	/**
	 * Sets the opt in.
	 *
	 * @param optIn            the optIn to set
	 */
	public void setOptIn(String optIn) {
		this.optIn = optIn;
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
	 * Gets the checks if is allowed.
	 *
	 * @return the isAllowed
	 */
	public String getIsAllowed() {
		return isAllowed;
	}

	/**
	 * Sets the checks if is allowed.
	 *
	 * @param isAllowed            the isAllowed to set
	 */
	public void setIsAllowed(String isAllowed) {
		this.isAllowed = isAllowed;
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

}
