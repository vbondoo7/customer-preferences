package com.cox.bis.customer.preference.exception;

public class SystemException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2084678439793806711L;

	public SystemException(){
		super();
	}
	
	public SystemException(Exception exception){
		super(exception);
	}
	
	public SystemException(String message, Exception exception){
		super(message, exception);
	}
	
	public SystemException(String message){
		super(message);
	}
}
