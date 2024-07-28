package com.foodfinder.food_finder.service;

import com.foodfinder.food_finder.model.AppUser;
import com.foodfinder.food_finder.model.UserRole;
import com.foodfinder.food_finder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getEmail())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }

    private String[] getRoles(AppUser user) {
        List<UserRole> userRoles = user.getUserRoles();
        List<String> stringUserRoles = userRoles.stream()
                .map(userRole -> userRole.getRole().getRoleEnum().name())
                .toList();
        String[] arrayUserRoles = new String[stringUserRoles.size()];
        for (int i = 0; i < arrayUserRoles.length; i++) {
            arrayUserRoles[i] = stringUserRoles.get(i);
        }
        return arrayUserRoles;
    }

    public AppUser signUpUser(AppUser user) throws InstanceAlreadyExistsException {
        boolean userExists = userRepository.findByEmail(user.getEmail())
                .isPresent();
        if (userExists) {
            throw new InstanceAlreadyExistsException("User exists.");
        }
        return userRepository.save(user);
    }


}
