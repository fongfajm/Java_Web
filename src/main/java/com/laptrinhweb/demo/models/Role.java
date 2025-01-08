package com.laptrinhweb.demo.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role")
    private Set<UserRoles> RolesUsers;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<UserRoles> getRolesUsers() {
        return RolesUsers;
    }
    public void setRolesUsers(Set<UserRoles> rolesUsers) {
        RolesUsers = rolesUsers;
    }
    public Role(int id, String name, Set<UserRoles> rolesUsers) {
        this.id = id;
        this.name = name;
        RolesUsers = rolesUsers;
    }
    public Role() {
    }

}
