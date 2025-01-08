package com.laptrinhweb.demo.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.Customer;
import com.laptrinhweb.demo.repositories.CustomerRepository;
import com.laptrinhweb.demo.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer createCustomer(Customer customer) {
        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        
        // Tạo đối tượng Customer với thông tin đã chỉnh sửa
        Customer savedCustomer = new Customer(
            customer.getUsername(),
            encodedPassword, // Sử dụng mật khẩu đã mã hóa
            customer.getEmail(),
            "customer" // Thiết lập role mặc định
        );

        // Lưu khách hàng vào cơ sở dữ liệu
        return customerRepository.save(savedCustomer);
    }
}
