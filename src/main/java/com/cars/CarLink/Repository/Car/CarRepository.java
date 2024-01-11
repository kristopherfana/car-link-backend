package com.cars.CarLink.Repository.Car;

import com.cars.CarLink.Model.Car.Attributes.Make;
import com.cars.CarLink.Model.Car.Attributes.Model;
import com.cars.CarLink.Model.Car.Car;
import com.cars.CarLink.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMake(Make make);
    List<Car> findByModel(Model model);
    List<Car> findByClient(Client client);
    Optional<Car> findByRegNumber(String regNumber);
    Boolean existsByRegNumber(String regNumber);
}
