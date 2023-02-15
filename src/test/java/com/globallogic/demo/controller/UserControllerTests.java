package com.globallogic.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.demo.controllers.UserController;
import com.globallogic.demo.dto.PhoneDto;
import com.globallogic.demo.dto.UserDto;
import com.globallogic.demo.exceptions.CustomServerException;
import com.globallogic.demo.exceptions.InvalidEmailException;
import com.globallogic.demo.exceptions.InvalidPasswordException;
import com.globallogic.demo.exceptions.RecordNotFoundException;
import com.globallogic.demo.exceptions.RepeatedUserException;
import com.globallogic.demo.model.Phone;
import com.globallogic.demo.model.User;
import com.globallogic.demo.services.IUserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

	@InjectMocks
	private UserController userController;
	@Mock
	private IUserService userService;

	private UserDto userDto;
	private PhoneDto phoneDto;
	private User user;
	private Phone phone;

	@BeforeEach
	public void setup() {
		phoneDto = new PhoneDto();
		phoneDto.setCityCode(381);
		phoneDto.setCountryCode(54);
		phoneDto.setNumber(234234032);
		List<PhoneDto> phonesDto = new ArrayList<PhoneDto>();
		phonesDto.add(phoneDto);
		userDto = new UserDto();
		userDto.setActive(true);
		userDto.setEmail("bla@bla.com");
		userDto.setName("usuario");
		userDto.setPassword("aa2sGffu2a");
		userDto.setPhones(phonesDto);
		userDto.setCreated(new Date());
		userDto.setId(1);
		userDto.setLastLogin(new Date());
		userDto.setToken("eyadfafdaadfa");
		phone = new Phone(1, (long) 2452452, 381, 54, user);
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(phone);
		user = new User(1, "usuario", "bla@bla.com", "12345", new Date(), new Date(), "eyafdafad", true, phones);
		phone = new Phone(1, (long) 83763626, 381, 54, user);

	}

	@DisplayName("JUnit test for signup method")
	@Test
	public void testSingUp() {

		try {
			given(userService.signUp(userDto)).willReturn(userDto);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UserDto userDtoResponse = null;
		try {
			userDtoResponse = userController.signUp(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThat(userDtoResponse).isNotNull();

	}

	@DisplayName("JUnit test for login method")
	@Test
	public void testLogin() {

		try {
			given(userService.login(1)).willReturn(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDto userDtoResponse = null;
		try {
			userDtoResponse = userController.login(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThat(userDtoResponse).isNotNull();
	}

	@DisplayName("JUnit test for handleInvalidEmailException method")
	@Test
	public void handleInvalidEmailExceptionTest() {
		try {
			given(userController.signUp(userDto)).willThrow(InvalidEmailException.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			userController.signUp(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertThat(userController.handleInvalidEmailException(new RuntimeException(e))).isNotNull();
		}
	}

	@DisplayName("JUnit test for handleInvalidPasswordException method")
	@Test
	public void handleInvalidPasswordExceptionTest() {
		try {
			given(userController.signUp(userDto)).willThrow(InvalidPasswordException.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			userController.signUp(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertThat(userController.handleInvalidPasswordException(new RuntimeException(e))).isNotNull();
		}
	}

	@DisplayName("JUnit test for handleRepeatedUserException method")
	@Test
	public void handleRepeatedUserExceptionTest() {
		try {
			given(userController.signUp(userDto)).willThrow(RepeatedUserException.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			userController.signUp(userDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertThat(userController.handleRepeatedUserException(new RuntimeException(e))).isNotNull();
		}
	}

	@DisplayName("JUnit test for handleRecordNotFoundException method")
	@Test
	public void handleRecordNotFoundExceptionTest() {
		try {
			given(userController.signUp(userDto)).willThrow(RecordNotFoundException.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			userController.signUp(userDto);
		} catch (Exception e) {
			e.printStackTrace();
			assertThat(userController.handleRecordNotFoundException(new RuntimeException(e))).isNotNull();
		}
	}
	
	@DisplayName("JUnit test for handleRecordNotFoundException method")
	@Test
	public void handleExceptionTest() {
		try {
			given(userController.signUp(userDto)).willThrow(CustomServerException.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			userController.signUp(userDto);
		} catch (Exception e) {
			e.printStackTrace();
			assertThat(userController.handleException(e)).isNotNull();
		}
	}

}
