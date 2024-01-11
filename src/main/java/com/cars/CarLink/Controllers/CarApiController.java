package com.cars.CarLink.Controllers;

import com.cars.CarLink.Model.Car.Attributes.*;
import com.cars.CarLink.Services.CarAPI.CarApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("car-api")
public class CarApiController {
    private final CarApiService carApiService;

    public CarApiController(CarApiService carApiService) {
        this.carApiService = carApiService;
    }

    @GetMapping("/makes")
    public ResponseEntity<List<Make>> getCarMakes() throws IOException, NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok().body(carApiService.getCarMakes());
    }

    @GetMapping("/makes/{id}")
    public ResponseEntity<Make> getMakeById(@PathVariable("id") Integer id) throws IOException, NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(carApiService.getMakeById(id));
    }

    @GetMapping("/models")
    public ResponseEntity<List<Model>> getModels(@RequestParam(
            "make_id") Integer id) throws IOException, NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(carApiService.getCarModel(id));
    }

    @GetMapping("/engines")
    public ResponseEntity<List<Engine>> getEngines(@RequestParam(
            "make_model_id") Integer id) throws IOException, NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(carApiService.getCarEngine(id));
    }

    @GetMapping("/bodies")
    public ResponseEntity<List<Body>> getBodies(@RequestParam(
            "make_model_id") Integer id) throws IOException, NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(carApiService.getCarBody(id));
    }

    @GetMapping("/interior-colors")
    public ResponseEntity<List<InteriorColor>> getInteriorColors(@RequestParam(
            "make_model_id") Integer id) throws IOException, NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(carApiService.getCarInteriorColor(id));
    }

    @GetMapping("/exterior-colors")
    public ResponseEntity<List<ExteriorColor>> getExteriorColors(@RequestParam(
            "make_model_id") Integer id) throws IOException, NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok(carApiService.getCarExteriorColor(id));
    }
}
