package com.wayl.paymybuddy.service.auth;

import com.wayl.paymybuddy.repository.ApplicationuserRepository;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.dto.ApplicationuserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class ApplicationUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationuserRepository applicationuserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //1, Override name as email:
        DaoApplicationUser user = applicationuserRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with Email: " + email);
        }
        //2, Load user by email:
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public DaoApplicationUser save(ApplicationuserDto applicationuserDto) {
        DaoApplicationUser user = new DaoApplicationUser();
        user.setUserName(applicationuserDto.getUserName());
        user.setEmail(applicationuserDto.getEmail());
        user.setPassword(encoder.encode(applicationuserDto.getPassword()));
        return applicationuserRepository.save(user);
    }
}