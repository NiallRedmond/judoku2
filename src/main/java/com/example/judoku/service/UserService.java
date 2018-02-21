package com.example.judoku.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.judoku.dto.UserRegistrationDto;
import com.example.judoku.model.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}