package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.model.DaoApplicationUser;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserService {
    List<DaoApplicationUser> findAll();
    Optional<DaoApplicationUser> findById(int id);
    DaoApplicationUser findByEmail(String email);
    Integer save(DaoApplicationUser user);
    void deleteById(int id);
    void deleteByEmail(String email);
}