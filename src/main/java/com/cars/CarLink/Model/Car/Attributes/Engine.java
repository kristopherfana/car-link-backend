package com.cars.CarLink.Model.Car.Attributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Engine {
    @Id
    public Integer id;
    private String fuelType;
    private String engineType;
    private String cylinders;
    private Integer horsepowerHp;
    private String transmission;

    public Engine() {
    }

    public Integer getId() {
        return id;
    }

    @JsonProperty("fuel_type")
    public String getFuelType() {
        return fuelType;
    }

    public String getCylinders() {
        return cylinders;
    }

    @JsonProperty("horsepower_hp")
    public Integer getHorsepowerHp() {
        return horsepowerHp;
    }

    @JsonProperty("engine_type")
    public String getEngineType() {
        return engineType;
    }

    public String getTransmission() {
        return transmission;
    }
}
