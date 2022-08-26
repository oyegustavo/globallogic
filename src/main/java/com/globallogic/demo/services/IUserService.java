package com.globallogic.demo.services;

import java.util.List;

import com.globallogic.demo.dto.UserDto;

public interface IUserService {
	public UserDto signUp(UserDto user) throws Exception;
	public UserDto login(Long userId) throws Exception;
	public List<UserDto> findAll() throws Exception;
}
