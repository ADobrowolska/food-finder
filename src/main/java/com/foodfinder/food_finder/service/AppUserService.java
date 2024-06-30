package com.foodfinder.food_finder.service;

import com.foodfinder.food_finder.model.User;
import com.foodfinder.food_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }

    public String signUpUser(User user) throws InstanceAlreadyExistsException {
        boolean userExists = userRepository.findByEmail(user.getEmail())
                .isPresent();
        if (userExists) {
            throw new InstanceAlreadyExistsException("User exists.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "SignUpUser";
    }


}
