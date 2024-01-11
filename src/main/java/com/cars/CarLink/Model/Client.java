package com.cars.CarLink.Model;

import com.cars.CarLink.Model.Car.Car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Car> cars;

    @Column(name="street_address")
    private String streetAddress;
    private String city;
    private String country;
    private String about;
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm")
    private LocalDateTime updatedAt;

    public Client(String email, String name, String streetAddress,
                  String city, String country, String phoneNumber,
                  String about) {
        this.email = email;
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.about=about;
    }

    public Client() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
