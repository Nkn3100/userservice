package org.scaler.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String email;
    private String hashedPassword;
    private String username;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private Name name;
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Address> address;
    private String phone;
    @ManyToMany
    private List<Role> roles;
    private Boolean isEmailVerified;


}
