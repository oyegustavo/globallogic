package com.globallogic.demo.exceptions;

import org.springframework.http.HttpStatus;

public class RepeatedUserException extends RuntimeException{
	private static final long serialVersionUID = -8466097313805420998L;
	private Integer httpStatusCode = HttpStatus.BAD_REQUEST.value();

	public RepeatedUserException() {
		super();
	}

	public RepeatedUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatedUserException(String message) {
		super(message);
	}
	
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
}
