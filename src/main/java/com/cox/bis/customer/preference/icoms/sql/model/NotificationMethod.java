package com.cox.bis.customer.preference.icoms.sql.model;

/**
 * @author Kumar Saurav
 *
 * 
 */
public class NotificationMethod {
	
	private String method;
	private String addressType;
	private String address;
	private String isMobile;
	private String isPrimary;
	private String serviceOptIn;
	private String optIn;
	private String isEditable;
	private String verificationStatus;
	private String verificationStatusDescription;
	private String verificationDate;
	private String verificationTime;
	private String confirmStatus;
	private String confirmDate;
	private String confirmTime;
    private String createdBy;
    private String createdDate;
    private String createdTime;
    private String changedBy;
    private String changedDate;
    private String changedTime;
    private String createdByMobile;
    private String createdDateMobile;
    private String createdTimeMobile;
    private String changedByMobile;
    private String changedDateMobile;
    private String changedTimeMobile;

	public String getVerificationTime() {
		return verificationTime;
	}
	public void setVerificationTime(String verificationTime) {
		this.verificationTime = verificationTime;
	}
	public String getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the isMobile
	 */
	public String getIsMobile() {
		return isMobile;
	}
	/**
	 * @param isMobile the isMobile to set
	 */
	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}
	/**
	 * @return the isPrimary
	 */
	public String getIsPrimary() {
		return isPrimary;
	}
	/**
	 * @param isPrimary the isPrimary to set
	 */
	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	/**
	 * @return the serviceOptIn
	 */
	public String getServiceOptIn() {
		return serviceOptIn;
	}
	/**
	 * @param serviceOptIn the serviceOptIn to set
	 */
	public void setServiceOptIn(String serviceOptIn) {
		this.serviceOptIn = serviceOptIn;
	}
	public String getOptIn() {
		return optIn;
	}
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
	 * @return the verificationStatus
	 */
	public String getVerificationStatus() {
		return verificationStatus;
	}
	/**
	 * @param verificationStatus the verificationStatus to set
	 */
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	/**
	 * @return the verificationStatusDescription
	 */
	public String getVerificationStatusDescription() {
		return verificationStatusDescription;
	}
	/**
	 * @param verificationStatusDescription the verificationStatusDescription to set
	 */
	public void setVerificationStatusDescription(String verificationStatusDescription) {
		this.verificationStatusDescription = verificationStatusDescription;
	}
	/**
	 * @return the verificationDate
	 */
	public String getVerificationDate() {
		return verificationDate;
	}
	/**
	 * @param verificationDate the verificationDate to set
	 */
	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}
	/**
	 * @return the confirmStatus
	 */
	public String getConfirmStatus() {
		return confirmStatus;
	}
	/**
	 * @param confirmStatus the confirmStatus to set
	 */
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	/**
	 * @return the confirmDate
	 */
	public String getConfirmDate() {
		return confirmDate;
	}
	/**
	 * @param confirmDate the confirmDate to set
	 */
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
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
	public String getCreatedByMobile() {
		return createdByMobile;
	}
	public void setCreatedByMobile(String createdByMobile) {
		this.createdByMobile = createdByMobile;
	}
	public String getCreatedDateMobile() {
		return createdDateMobile;
	}
	public void setCreatedDateMobile(String createdDateMobile) {
		this.createdDateMobile = createdDateMobile;
	}
	public String getCreatedTimeMobile() {
		return createdTimeMobile;
	}
	public void setCreatedTimeMobile(String createdTimeMobile) {
		this.createdTimeMobile = createdTimeMobile;
	}
	public String getChangedByMobile() {
		return changedByMobile;
	}
	public void setChangedByMobile(String changedByMobile) {
		this.changedByMobile = changedByMobile;
	}
	public String getChangedDateMobile() {
		return changedDateMobile;
	}
	public void setChangedDateMobile(String changedDateMobile) {
		this.changedDateMobile = changedDateMobile;
	}
	public String getChangedTimeMobile() {
		return changedTimeMobile;
	}
	public void setChangedTimeMobile(String changedTimeMobile) {
		this.changedTimeMobile = changedTimeMobile;
	}
	
    
}