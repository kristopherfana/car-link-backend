package com.cars.CarLink.Services.CarAPI;

import com.cars.CarLink.Model.Car.Attributes.*;

import java.io.IOException;
import java.util.List;

public interface CarApiService {

    List<Make> getCarMakes() throws IOException, NoSuchFieldException, IllegalAccessException;

    Make getMakeById(Integer id) throws IOException, NoSuchFieldException, IllegalAccessException;

    Model getModelById(Integer makeId, Integer makeModelId) throws IOException,
            NoSuchFieldException, IllegalAccessException;

    Body getBodyById(Integer makeModelId,
                     Integer bodyId) throws IOException,
            NoSuchFieldException, IllegalAccessException;

    Engine getEngineById(Integer makeModelId,
                         Integer engineId) throws IOException,
            NoSuchFieldException, IllegalAccessException;

    InteriorColor getInteriorColorById(Integer makeModelId,
                                       Integer interiorColorId) throws IOException,
            NoSuchFieldException, IllegalAccessException;

    ExteriorColor getExteriorColorById(Integer makeModelId,
                                       Integer exteriorColorId) throws IOException,
            NoSuchFieldException, IllegalAccessException;

    List<Model> getCarModel(Integer makeId) throws IOException, NoSuchFieldException, IllegalAccessException;

    List<Engine> getCarEngine(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException;

    List<Body> getCarBody(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException;

    List<InteriorColor> getCarInteriorColor(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException;

    List<ExteriorColor> getCarExteriorColor(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException;
}
