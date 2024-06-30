package com.foodfinder.food_finder.repository;

import com.foodfinder.food_finder.model.Role;
import com.foodfinder.food_finder.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleEnum(RoleEnum roleEnum);

}
