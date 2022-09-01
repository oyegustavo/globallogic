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
import com.globallogic.demo.utils.GLUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public UserDto signUp(UserDto userDto) throws Exception
	{
		UserDto result = null;
		if (userRepository.findByEmail(userDto.getEmail())!=null) {
			throw new RepeatedUserException("The user already exists!");
		}
		
		if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
			throw new InvalidEmailException("Invalid email address!");
		}
		if (!GLUtils.isValidPassword(userDto.getPassword())) {
			throw new InvalidPasswordException("Invalid password!");
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
				.orElseThrow(() -> new RecordNotFoundException("User Not Found!"));
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
