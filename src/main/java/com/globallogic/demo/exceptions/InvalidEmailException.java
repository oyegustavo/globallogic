package com.globallogic.demo.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends RuntimeException {
	private static final long serialVersionUID = 8108730150843317897L;
	
	private Integer httpStatusCode = HttpStatus.BAD_REQUEST.value();

	public InvalidEmailException() {
		super();
	}

	public InvalidEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEmailException(String message) {
		super(message);
	}
	
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
}
