package com.foodfinder.food_finder.dto.role;

import com.foodfinder.food_finder.model.Role;

public class RoleMapper {

    public static RoleDTO mapToRoleDTO(Role role) {
        return RoleDTO.builder()
                .roleEnum(role.getRoleEnum())
                .build();
    }
}
