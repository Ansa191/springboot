package com.examserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandling{
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(UserFoundException ex){
		String message =ex.getMessage();
		return new ResponseEntity<>(message,HttpStatus.FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler(UserNotFoundException ex){
		String message =ex.getMessage();
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}

}
