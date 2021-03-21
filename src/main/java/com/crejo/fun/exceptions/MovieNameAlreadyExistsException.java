package com.crejo.fun.exceptions;

public class MovieNameAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5596991167407718541L;
	private String message;
	
	public MovieNameAlreadyExistsException() {
		super();
	}

	public MovieNameAlreadyExistsException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
