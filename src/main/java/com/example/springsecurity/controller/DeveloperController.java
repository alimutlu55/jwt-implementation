package com.example.springsecurity.controller;

import com.example.springsecurity.exceptions.domain.UserNameExistException;
import com.example.springsecurity.models.Developer;
import com.example.springsecurity.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    @Autowired
    DeveloperRepository developerRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public DeveloperController(DeveloperRepository developerRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.developerRepository = developerRepository;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public void signUp (@RequestBody Developer developer) throws UserNameExistException {
        Developer findedUser = developerRepository.findByUserName(developer.getUserName());
        if(findedUser != null) throw new UserNameExistException("Bu username kullanılıyor.Başka bir username seçiniz.");
        developer.setPassword(bCryptPasswordEncoder.encode(developer.getPassword()));
        developerRepository.save(developer);
        System.out.println("Kaydoldu.");
    }
}
