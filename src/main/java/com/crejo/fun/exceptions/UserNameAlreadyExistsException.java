package com.crejo.fun.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6587410533404072577L;
	private String message;
	
	public UserNameAlreadyExistsException() {
		super();
	}
	public UserNameAlreadyExistsException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
}
