package com.globallogic.demo.exceptions;

import java.util.List;

import com.globallogic.demo.dto.ErrorDto;

import lombok.Data;

@Data
public class ErrorResponseDto{
	private List<ErrorDto> error;

	public ErrorResponseDto(List<ErrorDto> error) {
		this.error = error;
	}
}
