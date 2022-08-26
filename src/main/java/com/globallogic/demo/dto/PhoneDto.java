package com.globallogic.demo.dto;

import lombok.Data;

@Data
public class PhoneDto {
	private Long number;
	private Integer cityCode;
	private Integer countryCode;
}
