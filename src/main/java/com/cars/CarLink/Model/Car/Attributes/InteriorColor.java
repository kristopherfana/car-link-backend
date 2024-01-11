package com.cars.CarLink.Model.Car.Attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class InteriorColor {
    @Id
    public Integer id;
    public String name;
    private String rgb;

    public InteriorColor() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRgb() {
        return rgb;
    }
}
