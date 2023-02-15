package com.globallogic.demo.exceptions;

import org.springframework.http.HttpStatus;

public class CustomServerException extends Exception{
	private static final long serialVersionUID = 5794874346859777284L;
	
	private Integer httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

	public CustomServerException() {
		super();
	}

	public CustomServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomServerException(String message) {
		super(message);
	}
	
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
}
