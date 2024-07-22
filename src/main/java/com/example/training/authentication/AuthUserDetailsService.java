package com.example.training.authentication;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private final AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = userRepository.findByUsername(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(authUser.getUsername(), authUser.getPassword(), authUser.isActive(), true, true, true, Collections.emptyList());

    }
}

