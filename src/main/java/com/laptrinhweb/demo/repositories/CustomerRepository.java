package com.laptrinhweb.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.demo.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByEmail(String email);
}