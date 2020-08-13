package com.java.jwt.user.service;


import com.java.jwt.user.entity.User;

public interface UserService {

    User findByUsername(String username);

}
