package com.foodfinder.food_finder.dto.userrole;

import com.foodfinder.food_finder.dto.role.RoleMapper;
import com.foodfinder.food_finder.model.UserRole;

public class UserRoleMapper {

    public static UserRoleDTO mapToUserRoleDTO(UserRole userRole) {
        return UserRoleDTO.builder()
                .role(RoleMapper.mapToRoleDTO(userRole.getRole()))
                .build();
    }


}
