package com.cox.bis.customer.preference.exception;

public class AddressScrubClientException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1550304685815199886L;
	
	public AddressScrubClientException(String message) {
		super(message);
	}
	
	public AddressScrubClientException(Throwable throwable) {
		super(throwable);
	}
}