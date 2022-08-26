package com.globallogic.demo.controllers;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.demo.dto.PhoneDto;
import com.globallogic.demo.model.Phone;
import com.globallogic.demo.services.IPhoneService;


@RestController
@RequestMapping("/api/v1/phone")
public class PhoneController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IPhoneService phoneService;
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PhoneDto> savePhone(@RequestBody(required = true) PhoneDto phoneDto) {
    	Phone phone = modelMapper.map(phoneDto, Phone.class);
    	PhoneDto resultPhone = modelMapper.map(phoneService.save(phone), PhoneDto.class);
        return new ResponseEntity<>(resultPhone, HttpStatus.CREATED);
    }
	
    @GetMapping
    public ResponseEntity<List<Phone>> getAllPhones() {
        List<Phone> phones = phoneService.findAll();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

}
