package com.globallogic.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.demo.model.Phone;

@Repository
public interface IPhoneRepository extends CrudRepository<Phone, Long>{

}
