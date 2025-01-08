package com.laptrinhweb.demo.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.User;
import com.laptrinhweb.demo.repositories.UserRepository;
import com.laptrinhweb.demo.services.UserService;

@Service
public class UserServiceIpml implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}