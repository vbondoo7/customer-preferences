package com.cox.bis.customer.preference.exception;

public class FaultCodeException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String faultCode;

	/**
	 * Constructor
	 */
	public FaultCodeException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param message message
	 */
	public FaultCodeException(final String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause cause
	 */
	public FaultCodeException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message message
	 * @param cause cause
	 */
	public FaultCodeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
}