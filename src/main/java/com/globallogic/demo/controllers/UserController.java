package com.globallogic.demo.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.demo.dto.ErrorDto;
import com.globallogic.demo.dto.UserDto;
import com.globallogic.demo.exceptions.ErrorResponse;
import com.globallogic.demo.exceptions.RecordNotFoundException;
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
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
			result = Arrays.asList(new ErrorDto(errorResponse.getTimestamp(), errorResponse.getCode(), errorResponse.getMessage()));
			if (RecordNotFoundException.class.getName().equals(e.getClass().getName())) {
				errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
				result = Arrays.asList(new ErrorDto(errorResponse.getTimestamp(), errorResponse.getCode(), errorResponse.getMessage()));
			}
		}
		
		return result;
	}

	@PostMapping({ "sign-up" })
	public Object signUp(@RequestBody UserDto userDto) {

		Object result = null;
		try {
			result = userService.signUp(userDto);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
			result = Arrays.asList(new ErrorDto(errorResponse.getTimestamp(), errorResponse.getCode(), errorResponse.getMessage()));
			if (RuntimeException.class.getName().equals(e.getClass().getName())) {
				errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
				result = Arrays.asList(new ErrorDto(errorResponse.getTimestamp(), errorResponse.getCode(), errorResponse.getMessage()));
			}

		}
		return result;
	}

	@GetMapping
	public Object findAllUsers() {
		Object result = null;
		try {
			result = userService.findAll();
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
			result = Arrays.asList(new ErrorDto(errorResponse.getTimestamp(), errorResponse.getCode(), errorResponse.getMessage()));
			if (RuntimeException.class.getName().equals(e.getClass().getName())) {
				errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
				result = Arrays.asList(new ErrorDto(errorResponse.getTimestamp(), errorResponse.getCode(), errorResponse.getMessage()));
			}
		}
		return result;
	}

}
