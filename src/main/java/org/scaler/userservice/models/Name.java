package org.scaler.userservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Name extends BaseModel{
    private String firstName;
    private String lastName;
    @JsonBackReference
    @OneToMany(mappedBy = "name")
    private List<User> users;
}
