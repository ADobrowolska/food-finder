package com.foodfinder.food_finder.service;

import com.foodfinder.food_finder.model.AppUser;
import com.foodfinder.food_finder.model.Role;
import com.foodfinder.food_finder.model.RoleEnum;
import com.foodfinder.food_finder.model.UserRole;
import com.foodfinder.food_finder.repository.RoleRepository;
import com.foodfinder.food_finder.utils.EmailValidator;
import com.foodfinder.food_finder.utils.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;

@Service
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(EmailValidator emailValidator, AppUserService appUserService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.emailValidator = emailValidator;
        this.appUserService = appUserService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser register(RegistrationRequest request) throws InstanceAlreadyExistsException {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Invalid email address");
        }
        Role role = roleRepository.findByRoleEnum(RoleEnum.USER);
        AppUser user = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                new ArrayList<>()
        );
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        user.addRole(userRole);
        return appUserService.signUpUser(user);
    }
}
