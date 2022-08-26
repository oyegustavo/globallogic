package com.globallogic.demo.services;

import java.util.List;

import com.globallogic.demo.model.Phone;

public interface IPhoneService {
	public Phone save(Phone phone);
	public List<Phone> saveAll(List<Phone> phones);
	public List<Phone> findAll();
}
