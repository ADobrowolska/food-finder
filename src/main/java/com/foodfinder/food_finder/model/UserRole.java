package com.foodfinder.food_finder.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user_role")
@Getter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Role role;
    @ManyToOne
    private User user;

}
