package com.cars.CarLink.Model.Car.Attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {
    @Id
    public Integer id;
    public String type;
    private String doors;
    private String seats;

    public Body() {
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDoors() {
        return doors;
    }

    public String getSeats() {
        return seats;
    }
}
