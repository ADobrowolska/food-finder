package com.foodfinder.food_finder.dto.user;

import com.foodfinder.food_finder.dto.userrole.UserRoleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserRoleDTO> userRoleDTOs;

}
