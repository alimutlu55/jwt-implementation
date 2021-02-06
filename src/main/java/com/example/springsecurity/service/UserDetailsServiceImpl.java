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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Developer developer = developerRepository.findByUserName(userName);
        if(developer == null)
            throw new UsernameNotFoundException(userName);

        return new User(developer.getUserName(), developer.getPassword(), Collections.emptyList());
    }
}
