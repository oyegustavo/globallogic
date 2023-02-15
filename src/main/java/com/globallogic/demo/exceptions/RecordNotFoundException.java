package com.globallogic.demo.exceptions;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Integer httpStatusCode = HttpStatus.NOT_FOUND.value();

	public RecordNotFoundException(String message, Throwable err) {
		super(message, err);
	}

	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
}
