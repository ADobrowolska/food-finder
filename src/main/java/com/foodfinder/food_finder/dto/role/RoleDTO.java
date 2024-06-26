package com.foodfinder.food_finder.dto.role;

import com.foodfinder.food_finder.model.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleDTO {

    private RoleEnum roleEnum;

}
