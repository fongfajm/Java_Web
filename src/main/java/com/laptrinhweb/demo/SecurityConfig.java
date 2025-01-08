package com.laptrinhweb.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Password encoder bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (not recommended for production)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers( "/**").permitAll() // Public paths
                .requestMatchers("/admin/**").hasAuthority("ADMIN") // Admin-only paths
                .requestMatchers("/**").hasAuthority("customer") // User-specific paths
                .anyRequest().authenticated() // All other paths require authentication
            )
            .formLogin(login -> login
                .loginPage("/login") // Custom login page
                .loginProcessingUrl("/login") // Login processing endpoint
                .usernameParameter("username") // Username field in login form
                .passwordParameter("password") // Password field in login form
                .defaultSuccessUrl("/admin", true) // Default redirect after login
                .failureUrl("/home") // Redirect on login failure
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // Logout URL
                .logoutSuccessUrl("/login?logout=true") // Redirect after logout
                .deleteCookies("JSESSIONID") // Remove session cookie
                .invalidateHttpSession(true) // Invalidate session
            );

        return http.build();
    }

    // Web security customizer to ignore static resources
    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/static/**","/users/**","/admin/**","/product/**","/signin-up/**");
    }
}