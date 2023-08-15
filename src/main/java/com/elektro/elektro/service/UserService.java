package com.elektro.elektro.service;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.elektro.elektro.model.User;
import com.elektro.elektro.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
    User findUserById(Long id);
    List<User> getAllUsers();
    User saveUser(User user);
    User updateUser(User user);
    void deleteUserById(Long id);
    User findUserByEmail(String email);
}
