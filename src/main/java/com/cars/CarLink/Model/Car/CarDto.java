package com.cars.CarLink.Model.Car;

public class CarDto {
    private String regNumber;
    private Integer makeId;
    private Integer modelId;
    private Integer engineId;
    private Integer bodyId;
    private Integer exteriorColorId;
    private Integer interiorColorId;
    private String clientEmail;

    public CarDto() {
    }

    public String getRegNumber() {
        return regNumber;
    }
    public String getClientEmail() {
        return clientEmail;
    }
    public Integer getMakeId() {
        return makeId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public Integer getEngineId() {
        return engineId;
    }

    public Integer getBodyId() {
        return bodyId;
    }

    public Integer getExteriorColorId() {
        return exteriorColorId;
    }

    public Integer getInteriorColorId() {
        return interiorColorId;
    }
}
