package com.example.binarplus.web02.login;

import javax.validation.constraints.NotBlank;

public class LoginReq {
    @NotBlank(message = "USERNAME JANGAN KOSONG")
    private String username;
    @NotBlank(message = "PASSWORD JANGAN KOSONG")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
