package com.globallogic.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.demo.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer>{
}
