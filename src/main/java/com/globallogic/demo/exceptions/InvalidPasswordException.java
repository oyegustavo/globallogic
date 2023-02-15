package com.globallogic.demo.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends RuntimeException{
	private static final long serialVersionUID = -6842434560199254223L;
	
	private Integer httpStatusCode = HttpStatus.BAD_REQUEST.value();

	public InvalidPasswordException() {
		super();
	}

	public InvalidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidPasswordException(String message) {
		super(message);
	}
	
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
}
