package com.globallogic.demo.controllers;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.demo.dto.ErrorDto;
import com.globallogic.demo.dto.UserDto;
import com.globallogic.demo.exceptions.ErrorResponse;
import com.globallogic.demo.services.IUserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping({ "login/{id}" })
	public Object login(@PathVariable Integer id) throws Exception {
		Object result = null;
		try {
			result = userService.login(id);
		} catch (Exception e) {
			result = new ErrorResponse(
					Arrays.asList(new ErrorDto(new Date(), HttpStatus.NOT_FOUND.value(), e.getMessage())));
		}
		
		return result;
	}

	@PostMapping({ "sign-up" })
	public Object signUp(@RequestBody UserDto userDto) {

		Object result = null;
		try {
			result = userService.signUp(userDto);
		} catch (Exception e) {
			result = new ErrorResponse(
					Arrays.asList(new ErrorDto(new Date(), HttpStatus.BAD_REQUEST.value(), e.getMessage())));
		}
		return result;
	}

	@GetMapping
	public Object findAllUsers() {
		Object result = null;
		try {
			result = userService.findAll();
		} catch (Exception e) {
			result = new ErrorResponse(
					Arrays.asList(new ErrorDto(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage())));
		}
		return result;
	}

}
