package com.laptrinhweb.demo.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public CustomUserDetails() {
    }
    // Overloaded constructor
 public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
         super();  // Call the superclass constructor.
        this.user = user;
        this.authorities = authorities;
    }    
    // Getters and Setters
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return  true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
