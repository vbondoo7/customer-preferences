package com.cox.bis.customer.preference.icoms.sql.model;
/**
 * @author Kumar Saurav
 *
 * 
 */
public class NotificationPreference {
	
	private String method;
	private String methodDescription;
	private String category;
	private String categoryDescription;
	private String addressType;
	private String optIn;
	private String isEditable;
	private String isAllowed;
	private String createdBy;
	private String createdDate;
	private String createdTime;
	private String confirmDate;
	private String changedBy;
	private String changedDate;
	private String changedTime;
	
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMethodDescription() {
		return methodDescription;
	}
	public void setMethodDescription(String methodDescription) {
		this.methodDescription = methodDescription;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}
	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	/**
	 * @return the optIn
	 */
	public String getOptIn() {
		return optIn;
	}
	/**
	 * @param optIn the optIn to set
	 */
	public void setOptIn(String optIn) {
		this.optIn = optIn;
	}
	/**
	 * @return the isEditable
	 */
	public String getIsEditable() {
		return isEditable;
	}
	/**
	 * @param isEditable the isEditable to set
	 */
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}
	/**
	 * @return the isAllowed
	 */
	public String getIsAllowed() {
		return isAllowed;
	}
	/**
	 * @param isAllowed the isAllowed to set
	 */
	public void setIsAllowed(String isAllowed) {
		this.isAllowed = isAllowed;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public String getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}
	public String getChangedTime() {
		return changedTime;
	}
	public void setChangedTime(String changedTime) {
		this.changedTime = changedTime;
	}
	
	
	

}