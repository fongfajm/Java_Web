package com.laptrinhweb.demo.services;

import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.User;

@Service
public interface UserService {
    User findByUsername(String username);
}