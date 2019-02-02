package com.example.binarplus.web02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistExceptions extends RuntimeException {

    public UsernameAlreadyExistExceptions(String message) {
        super(message);
    }
}
