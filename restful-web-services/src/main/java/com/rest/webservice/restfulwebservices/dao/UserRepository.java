package com.rest.webservice.restfulwebservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservice.restfulwebservices.bean.User;

public interface UserRepository extends JpaRepository<User, Integer>{
 
}
