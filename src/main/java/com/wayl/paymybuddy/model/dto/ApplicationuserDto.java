package com.wayl.paymybuddy.model.dto;

import lombok.Getter;

@Getter
public class ApplicationuserDto {
    private String userName;
    private String email;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}