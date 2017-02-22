package com.cox.bis.customer.preference.exception;

public class InvalidSiteIdException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2084678439793806711L;

	public InvalidSiteIdException(){
		super();
	}
	
	public InvalidSiteIdException(Exception exception){
		super(exception);
	}
	
	public InvalidSiteIdException(String message, Exception exception){
		super(message, exception);
	}
	
	public InvalidSiteIdException(String message){
		super(message);
	}
}
