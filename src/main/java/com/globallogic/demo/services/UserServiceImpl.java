package com.globallogic.demo.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.globallogic.demo.dto.UserDto;
import com.globallogic.demo.exceptions.CustomServerException;
import com.globallogic.demo.exceptions.InvalidEmailException;
import com.globallogic.demo.exceptions.InvalidPasswordException;
import com.globallogic.demo.exceptions.RecordNotFoundException;
import com.globallogic.demo.exceptions.RepeatedUserException;
import com.globallogic.demo.model.Phone;
import com.globallogic.demo.model.User;
import com.globallogic.demo.repositories.IUserRepository;
import com.globallogic.demo.security.JwtTokenUtil;
import com.globallogic.demo.utils.Utils;

@Service
public class UserServiceImpl implements IUserService {
	
	private IUserRepository userRepository;
	private ModelMapper modelMapper;
	private BCryptPasswordEncoder passwordEncode;
	
	@Autowired
	public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper,
			BCryptPasswordEncoder passwordEncode) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncode = passwordEncode;
	}

	private static final String REPEATED_USER_EXCEPTION_MSG = "The user already exists!";
	private static final String INVALID_EMAIL_EXCEPTION_MSG="Invalid email address!";
	private static final String INVALID_PASSWORD_EXCEPTION_MSG="Invalid password!";
	private static final String RECORD_NOT_FOUND_EXCEPTION_MSG="User Not Found!";

	@Override
	public UserDto signUp(UserDto userDto) throws Exception
	{
		UserDto result = null;
		if (userRepository.findByEmail(userDto.getEmail())!=null) {
			throw new RepeatedUserException(REPEATED_USER_EXCEPTION_MSG);
		}
		
		if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
			throw new InvalidEmailException(INVALID_EMAIL_EXCEPTION_MSG);
		}
		if (!Utils.isValidPassword(userDto.getPassword())) {
			throw new InvalidPasswordException(INVALID_PASSWORD_EXCEPTION_MSG);
		}
		userDto.setPassword(passwordEncode.encode(userDto.getPassword()));
		try {
			User user = converToEntity(userDto);
			user.setCreated(new Date());
			user.setCreated(new Date());
			user.setActive(true);
			
			List<Phone> phones = user.getPhones();
			phones.stream().map(phone -> {
				phone.setUser(user);
				return phone;
			}).collect(Collectors.toList());
			userRepository.save(user);
			user.setToken(JwtTokenUtil.generateAccessToken(user));
			userRepository.save(user);
			result = convertToDto(user);
		} catch (Exception e) {
			throw new CustomServerException(e.getLocalizedMessage(), e.getCause());
		}
		return result;
	}

	@Override
	public UserDto login(Integer userId) throws Exception {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RecordNotFoundException(RECORD_NOT_FOUND_EXCEPTION_MSG));
		user.setLastLogin(new Date());
		return convertToDto(user);
	}

	public UserDto convertToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	public User converToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

}
