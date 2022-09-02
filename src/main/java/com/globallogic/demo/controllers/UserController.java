package com.globallogic.demo.controllers;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.demo.dto.ErrorDto;
import com.globallogic.demo.dto.ErrorResponseDto;
import com.globallogic.demo.dto.UserDto;
import com.globallogic.demo.exceptions.CustomServerException;
import com.globallogic.demo.exceptions.InvalidEmailException;
import com.globallogic.demo.exceptions.InvalidPasswordException;
import com.globallogic.demo.exceptions.RecordNotFoundException;
import com.globallogic.demo.exceptions.RepeatedUserException;
import com.globallogic.demo.services.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping({ "login/{id}" })
	public UserDto login(@PathVariable Integer id) throws Exception {
		return userService.login(id);
	}

	@PostMapping({ "sign-up" })
	public UserDto signUp(@RequestBody UserDto userDto) throws Exception {
		return userService.signUp(userDto);
	}

	@ExceptionHandler({ InvalidEmailException.class })
	public ErrorResponseDto handleInvalidEmailException(RuntimeException e) {
		InvalidEmailException invalidEmailException = new InvalidEmailException(e.getMessage(), e.getCause());
		return new ErrorResponseDto(Arrays.asList(new ErrorDto(new Date(), invalidEmailException.getHttpStatusCode(),
				invalidEmailException.getMessage())));
	}

	@ExceptionHandler({ InvalidPasswordException.class })
	public ErrorResponseDto handleInvalidPasswordException(RuntimeException e) {
		InvalidPasswordException invalidPasswordException = new InvalidPasswordException(e.getMessage(), e.getCause());
		return new ErrorResponseDto(Arrays.asList(new ErrorDto(new Date(), invalidPasswordException.getHttpStatusCode(),
				invalidPasswordException.getMessage())));
	}

	@ExceptionHandler({ RecordNotFoundException.class })
	public ErrorResponseDto handleRecordNotFoundException(RuntimeException e) {
		RecordNotFoundException recordNotFoundException = new RecordNotFoundException(e.getMessage(), e.getCause());
		return new ErrorResponseDto(Arrays.asList(new ErrorDto(new Date(), recordNotFoundException.getHttpStatusCode(),
				recordNotFoundException.getMessage())));
	}

	@ExceptionHandler({ RepeatedUserException.class })
	public ErrorResponseDto handleRepeatedUserException(RuntimeException e) {
		RepeatedUserException repeatedUserException = new RepeatedUserException(e.getMessage(), e.getCause());
		return new ErrorResponseDto(Arrays.asList(new ErrorDto(new Date(), repeatedUserException.getHttpStatusCode(),
				repeatedUserException.getMessage())));
	}

	@ExceptionHandler({ CustomServerException.class })
	public ErrorResponseDto handleException(Exception e) {
		CustomServerException ex = new CustomServerException(e.getMessage(), e.getCause());
		return new ErrorResponseDto(Arrays.asList(new ErrorDto(new Date(), ex.getHttpStatusCode(),
				ex.getMessage())));
	}

}
