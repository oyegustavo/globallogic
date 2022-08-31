package com.globallogic.demo.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserDto{
	private Integer id;
	private Date created;
	private Date lastLogin;
	private String token;
	private Boolean active;
	private String name;
	private String email;
	private String password;
	private List<PhoneDto> phones;
}
