package org.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GeoLocation extends BaseModel{
    private Double latitude;
    private Double longitude;
    @OneToOne(mappedBy = "geoLocation")
    private Address address;

}
