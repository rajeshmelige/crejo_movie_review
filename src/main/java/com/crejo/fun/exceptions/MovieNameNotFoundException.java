package com.crejo.fun.exceptions;

public class MovieNameNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6006195102377826624L;
	private String message;
	
	public MovieNameNotFoundException() {
		super();
	}
	
	public MovieNameNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
