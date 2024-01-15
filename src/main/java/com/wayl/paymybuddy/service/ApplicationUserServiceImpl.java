package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.repository.ApplicationuserRepository;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationuserRepository applicationuserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationUserServiceImpl(ApplicationuserRepository applicationuserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationuserRepository = applicationuserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<DaoApplicationUser> findAll() {
        return applicationuserRepository.findAll();
    }

    @Override
    public Optional<DaoApplicationUser> findById(int id) {
        return applicationuserRepository.findById(id);
    }

    @Override
    public DaoApplicationUser findByEmail(String email) {
        return applicationuserRepository.findByEmail(email);
    }

    @Override
    public Integer save(DaoApplicationUser user) {
        //UserDetailsService called to save UserDto as DaoUser, here is DaoUser saved As DaoUser, so it's not duplicate method.
        if (user != null) {
            DaoApplicationUser newUser = new DaoApplicationUser();
            newUser.setUserName(user.getUserName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return applicationuserRepository.save(newUser).getId();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        applicationuserRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        applicationuserRepository.deleteByEmail(email);
    }

}