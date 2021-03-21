package com.crejo.fun.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crejo.fun.exceptions.MovieNameAlreadyExistsException;
import com.crejo.fun.exceptions.MovieNameNotFoundException;
import com.crejo.fun.exceptions.MovieNotReleasedException;
import com.crejo.fun.exceptions.MultipleReviewException;
import com.crejo.fun.exceptions.NotAnUserException;
import com.crejo.fun.exceptions.UserNameAlreadyExistsException;

@ControllerAdvice
public class GlobalExceptionResolver {

	@ResponseBody
	@ExceptionHandler(value= {MovieNotReleasedException.class})
	public ResponseEntity handleMovieNotReleaseException(final Throwable ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(value= {MultipleReviewException.class})
	public ResponseEntity handleMultipleReviewException(final Throwable ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(value= {NotAnUserException.class})
	public ResponseEntity handleNotAnUserException(final Throwable ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ResponseBody
	@ExceptionHandler(value= {MovieNameNotFoundException.class})
	public ResponseEntity handleMovieNameNotFoundException(final Throwable ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(value= {UserNameAlreadyExistsException.class})
	public ResponseEntity handleUserNameAlreadyExistsException(final Throwable ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(value= {MovieNameAlreadyExistsException.class})
	public ResponseEntity handleMovieNameAlreadyExistsException(final Throwable ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(value= {ConstraintViolationException.class})
	public ResponseEntity handleConstraintViolationException(final Throwable ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}
