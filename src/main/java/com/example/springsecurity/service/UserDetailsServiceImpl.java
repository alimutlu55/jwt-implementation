package com.example.springsecurity.service;

import com.example.springsecurity.models.Developer;
import com.example.springsecurity.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Qualifier("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private DeveloperRepository developerRepository;

    public UserDetailsServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Developer developer = developerRepository.findByUsername(username);
        if(developer == null)
            throw new UsernameNotFoundException(username);

        return new User(developer.getUsername(), developer.getPassword(), Collections.emptyList());
    }
}
