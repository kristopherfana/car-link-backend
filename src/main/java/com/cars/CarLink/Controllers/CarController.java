package com.cars.CarLink.Controllers;

import com.cars.CarLink.Model.Car.Attributes.Make;
import com.cars.CarLink.Model.Car.Car;
import com.cars.CarLink.Model.Car.CarDto;
import com.cars.CarLink.Services.Car.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> create(@RequestPart CarDto carDto,
                                    @RequestPart(value = "file",
                                            required = false) MultipartFile picture) throws IOException, NoSuchFieldException, IllegalAccessException {
        if(carService.existsByRegNumber(carDto.getRegNumber())){
            return ResponseEntity.ok().body("Registration number " +
                    "already exists.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.createCar(carDto, picture));
    }
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity.ok().body(carService.getAllCars());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        carService.deleteCar(id);
        return ResponseEntity.ok("Car deleted successfully. Car Id"+id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(carService.findById(id));
    }
}
