package com.globallogic.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDto {
	private Date timestamp;
	private Integer codigo;
	private String detail;
	
	public ErrorDto(Date timestamp, Integer codigo, String detail) {
		this.timestamp = timestamp;
		this.codigo = codigo;
		this.detail = detail;
	}
	
	public ErrorDto() {
	}


}



