package com.wayl.paymybuddy.model.dto;


import com.wayl.paymybuddy.model.DaoApplicationUser;
import org.springframework.web.bind.annotation.ModelAttribute;

public class FriendDto {
    private DaoApplicationUser user;
    private String friendEmail;

    public DaoApplicationUser getUser() {
        return user;
    }

    public void setUser(DaoApplicationUser user) {
        this.user = user;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }



}