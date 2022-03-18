package com.stellaweb.webdemo.models.data;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import com.stellaweb.webdemo.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}