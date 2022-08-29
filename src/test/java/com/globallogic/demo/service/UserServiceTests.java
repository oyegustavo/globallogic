package com.globallogic.demo.service;

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
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.globallogic.demo.dto.PhoneDto;
import com.globallogic.demo.dto.UserDto;
import com.globallogic.demo.model.Phone;
import com.globallogic.demo.model.User;
import com.globallogic.demo.repositories.IUserRepository;
import com.globallogic.demo.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	@Mock
	private IUserRepository userRepository;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private BCryptPasswordEncoder passwordEncode;

	@InjectMocks
	private UserServiceImpl userService;

	private User user;
	private Phone phone;
	private UserDto userDto;
	private PhoneDto phoneDto;

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
	public void givenUserDto_whenSignUpUser_thenReturnUser() {

		given(userRepository.save(user)).willReturn(user);
		given(userService.converToEntity(userDto)).willReturn(user);
		given(userService.convertToDto(user)).willReturn(userDto);
		given(passwordEncode.encode(userDto.getPassword()))
				.willReturn("$2a$10$RsLRQaoOviQF8aIQFKthxObu0SPLEnnV7apxkcSK/LqlwJ7w59iwK");

		System.out.println(userRepository);
		System.out.println(userService);

		UserDto savedUser;
		try {
			savedUser = userService.signUp(userDto);
			System.out.println(savedUser);
			assertThat(savedUser).isNotNull();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
