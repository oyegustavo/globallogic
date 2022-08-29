package com.globallogic.demo.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.demo.dto.UserDto;
import com.globallogic.demo.exceptions.RecordNotFoundException;
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

	@Override
	public UserDto signUp(UserDto userDto) throws Exception {
		UserDto result = null;
		if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
			throw new Exception("Invalid email address!");
		}
		if (!GLUtils.isValidPassword(userDto.getPassword())) {
			throw new Exception("Invalid password!");
		}
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
			throw new Exception(e.getLocalizedMessage());
		}
		return result;
	}

	@Override
	public UserDto login(Integer userId) throws Exception {
		return convertToDto(userRepository.findById(userId)
				.orElseThrow(() -> new RecordNotFoundException("User id " + userId + " Not Found")));
	}

	@Override
	public List<UserDto> findAll() throws Exception {
		List<User> users = (List<User>) userRepository.findAll();
		if (users == null) {
			throw new Exception("Users not found");
		}
		List<UserDto> dtos = users.stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return dtos;
	}

	private UserDto convertToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	private User converToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

}
