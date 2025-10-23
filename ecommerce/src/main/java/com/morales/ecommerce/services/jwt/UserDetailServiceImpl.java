package com.morales.ecommerce.services.auth.jwt;

import com.morales.ecommerce.entities.User;
import com.morales.ecommerce.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //write logic to get user from db
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        if(optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found", null);
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),optionalUser.get().getPassword(), new ArrayList<>());
    }
}
