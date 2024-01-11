package com.cars.CarLink.Services.Car;

import com.cars.CarLink.Model.Car.Car;
import com.cars.CarLink.Model.Car.CarDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    Car findById(Long id);
    Car createCar(CarDto carDto, MultipartFile picture) throws IOException, NoSuchFieldException, IllegalAccessException;
    Car updateCar(Long id, CarDto carDto);
    List<Car> getAllCars();
    List<Car> getAllCarsByClient(Long clientId);
    void deleteCar(Long id);
    Car findByRegNumber(String regNumber);
    Boolean existsByRegNumber(String regNumber);

}
