package org.scaler.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel{
    private String city;
    private String street;
    private Integer number;
    private String zipCode;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private GeoLocation geoLocation;
    @ManyToOne()
    @JsonIgnore
    private User user;

}
