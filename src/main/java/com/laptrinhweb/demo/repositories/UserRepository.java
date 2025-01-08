package com.laptrinhweb.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.demo.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer>{
    User findByUsername(String username);
}
