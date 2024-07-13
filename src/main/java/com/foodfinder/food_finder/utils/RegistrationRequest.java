package com.foodfinder.food_finder.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Builder
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
