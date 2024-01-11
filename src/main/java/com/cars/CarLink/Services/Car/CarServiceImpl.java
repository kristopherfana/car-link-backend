package com.cars.CarLink.Services.Car;

import com.cars.CarLink.Exceptions.NotFoundException;
import com.cars.CarLink.Model.Car.Attributes.*;
import com.cars.CarLink.Model.Car.Car;
import com.cars.CarLink.Model.Car.CarDto;
import com.cars.CarLink.Model.Client;
import com.cars.CarLink.Repository.Car.Attributes.*;
import com.cars.CarLink.Repository.Car.CarRepository;
import com.cars.CarLink.Services.CarAPI.CarApiService;
import com.cars.CarLink.Services.Client.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CarServiceImpl implements CarService{

    private final String UPLOAD_DIRECTORY;
    private final CarRepository carRepository;
    private final ClientService clientService;
    private final CarApiService carApiService;
    private final MakeRepository makeRepository;
    private final BodyRepository bodyRepository;
    private final ModelRepository modelRepository;
    private final ExteriorColorRepository exteriorColorRepository;
    private final InteriorColorRepository interiorColorRepository;
    private final EngineRepository engineRepository;

    public CarServiceImpl(@Value("${car.upload.directory}") String uploadDirectory, CarRepository carRepository, ClientService clientService, CarApiService carApiService, MakeRepository makeRepository, BodyRepository bodyRepository, ModelRepository modelRepository, ExteriorColorRepository exteriorColorRepository, InteriorColorRepository interiorColorRepository, EngineRepository engineRepository) {
        this.UPLOAD_DIRECTORY = uploadDirectory;
        this.carRepository = carRepository;
        this.clientService = clientService;
        this.carApiService = carApiService;
        this.makeRepository = makeRepository;
        this.bodyRepository = bodyRepository;
        this.modelRepository = modelRepository;
        this.exteriorColorRepository = exteriorColorRepository;
        this.interiorColorRepository = interiorColorRepository;
        this.engineRepository = engineRepository;
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()-> new NotFoundException("Car not found. Car Id: "+id));
    }

    @Override
    public Car createCar(CarDto carDto, MultipartFile picture) throws IOException, NoSuchFieldException, IllegalAccessException {
        Car car=convertDtoToCar(carDto);
        saveCarAttributes(
                car.getMake(),
                car.getModel(),
                car.getBody(),
                car.getEngine(),
                car.getInteriorColor(),
                car.getExteriorColor()
        );
        if (picture != null && !picture.isEmpty()) {
            String pictureFilePath = uploadPicture(picture);
            car.setPictureFileName(pictureFilePath);
        }
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, CarDto carDto) {
        return null;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAllCarsByClient(Long clientId) {
        Client client=clientService.findById(clientId);
        return carRepository.findByClient(client);
    }

    @Override
    public void deleteCar(Long id) {
        Car car=this.findById(id);
        carRepository.delete(car);
    }

    @Override
    public Car findByRegNumber(String regNumber) {
        return null;
    }

    @Override
    public Boolean existsByRegNumber(String regNumber) {
        return carRepository.existsByRegNumber(regNumber);
    }

    private String uploadPicture(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            Path filePath = Paths.get(UPLOAD_DIRECTORY, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload the file", e);
        }
    }

    private void saveCarAttributes(Make make, Model model,
                                   Body body, Engine engine,
                                   InteriorColor interiorColor,
                                   ExteriorColor exteriorColor){
        if(!makeRepository.existsById(make.getId())) makeRepository.save(make);
        if(!modelRepository.existsById(model.getId())) modelRepository.save(model);
        if(!bodyRepository.existsById(body.getId())) bodyRepository.save(body);
        if(!engineRepository.existsById(engine.getId())) engineRepository.save(engine);
        if(!interiorColorRepository.existsById(interiorColor.getId())) interiorColorRepository.save(interiorColor);
        if(!exteriorColorRepository.existsById(exteriorColor.getId())) exteriorColorRepository.save(exteriorColor);
    }

    private Car convertDtoToCar(CarDto carDto) throws IOException,
            NoSuchFieldException, IllegalAccessException {
        Make make= carApiService.getMakeById(carDto.getMakeId());
        Client client = clientService.findByEmail(carDto.getClientEmail());
        Model model= carApiService.getModelById(carDto.getMakeId()
                , carDto.getModelId());
        Body body= carApiService.getBodyById(carDto.getModelId(),
                carDto.getBodyId());
        Engine engine=
                carApiService.getEngineById(carDto.getModelId(),
                        carDto.getEngineId());
        InteriorColor interiorColor=
                carApiService.getInteriorColorById(carDto.getModelId(),carDto.getInteriorColorId());
        ExteriorColor exteriorColor=
                carApiService.getExteriorColorById(carDto.getModelId(),carDto.getExteriorColorId());
        return  new Car(
                        carDto.getRegNumber(),
                        client,
                        make,
                        model,
                        interiorColor,
                        exteriorColor,
                        engine,
                        body
                );
    }
}
