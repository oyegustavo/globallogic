package com.globallogic.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponseDto{
	private List<ErrorDto> error;

	public ErrorResponseDto(List<ErrorDto> error) {
		this.error = error;
	}
}
