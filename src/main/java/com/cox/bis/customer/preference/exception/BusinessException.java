package com.cox.bis.customer.preference.exception;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2084678439793806711L;

	public BusinessException(){
		super();
	}
	
	public BusinessException(Exception exception){
		super(exception);
	}
	
	public BusinessException(String message, Exception exception){
		super(message, exception);
	}
	
	public BusinessException(String message){
		super(message);
	}
}
