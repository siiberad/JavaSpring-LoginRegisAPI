package com.example.binarplus.web02.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

public class UsernameAlreadyExistResponse {

        private String username;

        public UsernameAlreadyExistResponse(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
}
