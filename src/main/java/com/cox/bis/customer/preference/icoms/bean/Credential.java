package com.cox.bis.customer.preference.icoms.bean;
/***
 * 
 * @author Durgesh
 *
 */
public class Credential {

	/***
	 * 
	 */
	private String username;
	/***
	 * 
	 */
	private String encryptedPassword;
	/***
	 * 
	 */
	private String decryptedPassword;
	
	private Boolean isValidated;
	
	public Credential () {
	}
	
	public Credential (String username) {
		this.username = username;
	}
	
	public Credential (String username, String decryptedPassword) {
		this.username = username;
		this.decryptedPassword = decryptedPassword;
	}
	
	public Credential (String username, String decryptedPassword, String encryptedPassword) {
		this.username = username;
		this.decryptedPassword = decryptedPassword;
		this.encryptedPassword = encryptedPassword;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the encryptedPassword
	 */
	public String getPassword() {
		return encryptedPassword;
	}
	/**
	 * @param encryptedPassword the encryptedPassword to set
	 */
	public void setPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	/**
	 * @return the decryptedPassword
	 */
	public String getDecryptedPassword() {
		return decryptedPassword;
	}
	/**
	 * @param decryptedPassword the decryptedPassword to set
	 */
	public void setDecryptedPassword(String decryptedPassword) {
		this.decryptedPassword = decryptedPassword;
	}

	/**
	 * @return the isValidated
	 */
	public Boolean getIsValidated() {
		return isValidated;
	}

	/**
	 * @param isValidated the isValidated to set
	 */
	public void setIsValidated(Boolean isValidated) {
		this.isValidated = isValidated;
	}
	
	
}