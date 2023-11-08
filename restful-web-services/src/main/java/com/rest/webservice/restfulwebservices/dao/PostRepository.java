package com.rest.webservice.restfulwebservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.webservice.restfulwebservices.bean.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{
 
}
