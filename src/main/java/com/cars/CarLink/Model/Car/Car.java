package com.cars.CarLink.Model.Car;

import com.cars.CarLink.Model.Car.Attributes.*;
import com.cars.CarLink.Model.Client;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name= "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="reg_number", nullable = false, unique = true)
    private String regNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "make_id",referencedColumnName = "id")
    private Make make;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id",referencedColumnName = "id")
    private Model model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interior_color_id",referencedColumnName =
            "id")
    private InteriorColor interiorColor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exterior_color_id",referencedColumnName =
            "id")
    private ExteriorColor exteriorColor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id",referencedColumnName = "id")
    private Engine engine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "body_id",referencedColumnName = "id")
    private Body body;

    @Column
    private String pictureFileName;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd-mm-yyyy hh:mm")
    private Instant updatedAt;

    public Car() {
    }

    public Car(String regNumber, Client client, Make make, Model model, InteriorColor interiorColor, ExteriorColor exteriorColor, Engine engine, Body body) {
        this.regNumber = regNumber;
        this.client = client;
        this.make = make;
        this.model = model;
        this.interiorColor = interiorColor;
        this.exteriorColor = exteriorColor;
        this.engine = engine;
        this.body = body;
    }

    public Car(String regNumber, Client client, Make make, Model model, InteriorColor interiorColor, ExteriorColor exteriorColor, Engine engine, Body body, String pictureFileName) {
        this.regNumber = regNumber;
        this.client = client;
        this.make = make;
        this.model = model;
        this.interiorColor = interiorColor;
        this.exteriorColor = exteriorColor;
        this.engine = engine;
        this.body = body;
        this.pictureFileName = pictureFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public InteriorColor getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(InteriorColor interiorColor) {
        this.interiorColor = interiorColor;
    }

    public ExteriorColor getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(ExteriorColor exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
