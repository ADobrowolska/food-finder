package com.foodfinder.food_finder.controller;


import com.foodfinder.food_finder.service.RegistrationService;
import com.foodfinder.food_finder.utils.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest request) throws InstanceAlreadyExistsException {
        return registrationService.register(request);
    }


}
