package com.crejo.fun.exceptions;

public class MovieNotReleasedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5273935000584229435L;
	
	private String message;
	
	public MovieNotReleasedException() {
		super();
	}
	
	public MovieNotReleasedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
