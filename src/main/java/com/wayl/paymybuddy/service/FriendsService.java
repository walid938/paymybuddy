package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.Friends;

import java.util.List;
import java.util.Set;

public interface FriendsService {
    void save(Friends friends);
    Friends findByFriend_Id(int id);
    List<Friends> findAllByUser_Id(int id);
    Integer deleteByFriend_Id(int id);
    Integer isFriend(int id1, int id2);
    Set<DaoApplicationUser> findAllMyFriends(int id);
}