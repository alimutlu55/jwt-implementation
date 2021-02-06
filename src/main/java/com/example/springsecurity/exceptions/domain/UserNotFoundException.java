package com.example.springsecurity.exceptions.domain;

public class UserNotFoundException extends Exception {
    public UserNotFoundException (String message) {
        super(message);
    }
}
