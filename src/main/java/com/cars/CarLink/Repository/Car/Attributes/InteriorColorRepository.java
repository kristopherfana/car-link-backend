package com.cars.CarLink.Repository.Car.Attributes;

import com.cars.CarLink.Model.Car.Attributes.InteriorColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface InteriorColorRepository extends JpaRepository<InteriorColor,
        Integer> {
    boolean existsById(@NonNull Integer id);
}
