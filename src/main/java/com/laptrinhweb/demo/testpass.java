package com.laptrinhweb.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testpass {
    public static void main(String[] args) {
        // Tạo đối tượng BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Mật khẩu gốc cần mã hóa
        String rawPassword = "123456";

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // In kết quả
        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);

        // Kiểm tra xem mật khẩu gốc có khớp với mật khẩu mã hóa hay không
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + isMatch);
    }
}
