package com.cars.CarLink.Controllers;

import com.cars.CarLink.Exceptions.NotFoundException;
import com.cars.CarLink.Model.Car.Car;
import com.cars.CarLink.Model.Client;
import com.cars.CarLink.Model.ClientDto;
import com.cars.CarLink.Services.Car.CarService;
import com.cars.CarLink.Services.Client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;
    private final CarService carService;

    public ClientController(ClientService clientService, CarService carService) {
        this.clientService = clientService;
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto){
        if(clientService.existsByEmail(clientDto.getEmail())){
            return ResponseEntity.ok().body("Email is already taken" +
                    ".");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDto));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws NotFoundException {
        clientService.deleteClient(id);
        return ResponseEntity.ok().body("Client deleted succesfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @GetMapping("{id}/cars")
    public ResponseEntity<List<Car>> getCarsByClient(@PathVariable(
            "id") Long clientId) throws NotFoundException {
        return ResponseEntity.ok().body(carService.getAllCarsByClient(clientId));
    }
}
