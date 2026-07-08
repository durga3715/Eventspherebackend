package com.example.eventsphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.eventsphere.entity.user;
import com.example.eventsphere.exception.ResourceNotFound;
import com.example.eventsphere.repository.userrepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private userrepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        user userData = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User not Found"));

        return User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .roles(userData.getRole().name())
                .build();
    }
}