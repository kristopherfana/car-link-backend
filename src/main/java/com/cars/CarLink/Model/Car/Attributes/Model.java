package com.cars.CarLink.Model.Car.Attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Model {

    @Id
    public Integer id;
    public Integer makeId;
    public String name;

    public Integer getId() {
        return id;
    }

    @JsonProperty("make_id")
    public Integer getMakeId() {
        return makeId;
    }

    public String getName() {
        return name;
    }
}
