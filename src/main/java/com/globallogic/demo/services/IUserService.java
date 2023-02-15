package com.globallogic.demo.services;

import com.globallogic.demo.dto.UserDto;

public interface IUserService {
	public UserDto signUp(UserDto user) throws Exception;
	public UserDto login(Integer userId) throws Exception;
}
