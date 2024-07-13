package com.foodfinder.food_finder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles = new ArrayList<>();

    public AppUser(String firstName, String lastName, String email, String password, List<UserRole> userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRoles = userRole;
    }


    public void addRole(UserRole role) {
        if(userRoles != null) {
            userRoles.add(role);
            role.setUser(this);
        }
    }
}
