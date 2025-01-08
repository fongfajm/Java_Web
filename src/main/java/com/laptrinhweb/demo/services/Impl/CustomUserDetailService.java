package com.laptrinhweb.demo.services.Impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.CustomUserDetails;
import com.laptrinhweb.demo.models.User;
import com.laptrinhweb.demo.models.UserRoles;
import com.laptrinhweb.demo.services.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null){
        throw new UsernameNotFoundException("sai");
    }
    Collection<GrantedAuthority> GrantedAuthoritySet = new HashSet<>();
    Set<UserRoles> roles = user.getUserRoles();
    for (UserRoles userRole : roles) {
        GrantedAuthoritySet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
    }
    return new CustomUserDetails(user,GrantedAuthoritySet);
}
}
