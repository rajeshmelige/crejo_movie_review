package com.crejo.fun.exceptions;

public class NotAnUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 593438147775214832L;
	
	private String message;
	
	public NotAnUserException() {
		super();
	}
	
	public NotAnUserException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
