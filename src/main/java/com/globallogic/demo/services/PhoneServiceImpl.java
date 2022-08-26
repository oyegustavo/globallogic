package com.globallogic.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.demo.model.Phone;
import com.globallogic.demo.repositories.IPhoneRepository;

@Service
public class PhoneServiceImpl implements IPhoneService{
	
	@Autowired
	private IPhoneRepository phoneRepository;

	@Override
	public Phone save(Phone phone) {
		return phoneRepository.save(phone);
	}

	@Override
	public List<Phone> findAll() {
		return (List<Phone>) phoneRepository.findAll();
	}

	@Override
	public List<Phone> saveAll(List<Phone> phones) {
		return (List<Phone>) phoneRepository.saveAll(phones);
	}

}
