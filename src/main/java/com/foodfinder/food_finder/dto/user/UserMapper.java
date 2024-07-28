package com.foodfinder.food_finder.dto.user;

import com.foodfinder.food_finder.dto.userrole.UserRoleDTO;
import com.foodfinder.food_finder.dto.userrole.UserRoleMapper;
import com.foodfinder.food_finder.model.AppUser;
import com.foodfinder.food_finder.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public static UserDTO mapToUserDTO(AppUser appUser) {
        return UserDTO.builder()
                .id(appUser.getId())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .userRoleDTOs(mapToUserRoleDTO(appUser.getUserRoles()))
                .build();
    }

    public static List<UserRoleDTO> mapToUserRoleDTO(List<UserRole> userRoles) {
        return userRoles.stream()
                .map(UserRoleMapper::mapToUserRoleDTO)
                .toList();
    }

}
