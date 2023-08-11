package com.demos.paymybuddy.service.impl;

import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service @RequiredArgsConstructor @Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Check cridentials for : " + username);
        CustomUser user = this.userRepository.findByUsername(username);

        if (user != null) {
            log.info("Cridentials Valid : " + username);
            return new User(user.getEmail(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER")));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }

    }
}
