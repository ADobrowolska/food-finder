package com.foodfinder.food_finder.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "role")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

}
