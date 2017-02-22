package com.cox.bis.customer.preference.exception;

public class RequiredFieldException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2084678439793806711L;

	public RequiredFieldException(){
		super();
	}
	
	public RequiredFieldException(Exception exception){
		super(exception);
	}
	
	public RequiredFieldException(String message, Exception exception){
		super(message, exception);
	}
	
	public RequiredFieldException(String message){
		super(message);
	}
}
