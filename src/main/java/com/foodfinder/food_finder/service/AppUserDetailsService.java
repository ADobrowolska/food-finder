package com.foodfinder.food_finder.service;

import com.foodfinder.food_finder.model.User;
import com.foodfinder.food_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .firstName(userObj.getFirstName())
                    .lastName(userObj.getLastName())
                    .email(userObj.getEmail())
                    .password(userObj.getPassword())
                    .userRole(userObj.getUserRole())
                    .build();
        } else {
            throw new UsernameNotFoundException(email);
        }
    }


}
