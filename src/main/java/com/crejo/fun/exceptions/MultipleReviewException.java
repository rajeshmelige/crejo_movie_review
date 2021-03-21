package com.crejo.fun.exceptions;

public class MultipleReviewException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4846656324365565L;
	private String message;
	
	public MultipleReviewException() {
		super();
	}
	
	public MultipleReviewException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
