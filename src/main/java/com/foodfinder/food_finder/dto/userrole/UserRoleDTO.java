package com.foodfinder.food_finder.dto.userrole;

import com.foodfinder.food_finder.dto.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRoleDTO {

    private RoleDTO role;

}
