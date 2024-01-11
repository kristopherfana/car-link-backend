package com.cars.CarLink.Services.CarAPI;

import com.cars.CarLink.Model.Car.Attributes.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarApiServiceImpl implements CarApiService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final String CAR_API_URL="https://carapi.app/api";

    public static <T> List<T> getListFromJson(String url,
                                              String dataField,
                                              TypeReference<List<T>> typeReference, String type) throws IOException, NoSuchFieldException, IllegalAccessException {
        String json = restTemplate.getForObject(url, String.class);
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode dataNode = rootNode.get(dataField);
        List<T> result=objectMapper.readValue(dataNode.toString(),
                typeReference);
        // Create a map to store items by name
        Map<String, T> itemsByNameOrType = new LinkedHashMap<>();

        if(type!=null){
            for (T object : result) {
                // Extract the name property using appropriate getter method or field access
                String nameOrType = null;
                if (object.getClass().getField(type).getType().equals(String.class)) {
                    nameOrType=
                            (String) object.getClass().getField(type).get(object);
                    itemsByNameOrType.put(nameOrType, object);
                }
            }
            // Convert the map values back to a list
            return new ArrayList<>(itemsByNameOrType.values());
        }
        return result;

    }

    public static <T> T getObjectById(List<T> objects, Integer id) throws NoSuchFieldException, IllegalAccessException {
        for (T object : objects) {
            if (object.getClass().getField("id").getType().equals(Integer.class)) {
                Integer objectId=
                        (Integer) object.getClass().getField("id").get(object);
                if (objectId.equals(id)) {
                    return object;
                }
            }
        }
        return null;
    }

    @Override
    public List<Make> getCarMakes() throws IOException, NoSuchFieldException, IllegalAccessException {
        return getListFromJson(this.CAR_API_URL+"/makes","data",
                new TypeReference<List<Make>>(){},"name");
    }

    @Override
    public Make getMakeById(Integer id) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Make> makes=getCarMakes();
        return getObjectById(makes, id);
    }

    @Override
    public Model getModelById(Integer makeId, Integer makeModelId) throws IOException,
            NoSuchFieldException, IllegalAccessException {
        List<Model> models=getCarModel(makeId);
        return getObjectById(models, makeModelId);
    }

    @Override
    public Body getBodyById(Integer makeModelId,
                            Integer bodyId) throws IOException,
            NoSuchFieldException, IllegalAccessException {
        List<Body> bodies=getCarBody(makeModelId);
        return getObjectById(bodies, bodyId);
    }

    @Override
    public Engine getEngineById(Integer makeModelId,
                                Integer engineId) throws IOException,
            NoSuchFieldException, IllegalAccessException {
        List<Engine> engines=getCarEngine(makeModelId);
        return getObjectById(engines, engineId);
    }

    @Override
    public InteriorColor getInteriorColorById(Integer makeModelId,
                            Integer interiorColorId) throws IOException,
            NoSuchFieldException, IllegalAccessException {
        List<InteriorColor> interiorColors=
                getCarInteriorColor(makeModelId);
        return getObjectById(interiorColors, interiorColorId);
    }

    @Override
    public ExteriorColor getExteriorColorById(Integer makeModelId,
                            Integer exteriorColorId) throws IOException,
            NoSuchFieldException, IllegalAccessException {
        List<ExteriorColor> exteriorColors=
                getCarExteriorColor(makeModelId);
        return getObjectById(exteriorColors, exteriorColorId);
    }

    @Override
    public List<Model> getCarModel(Integer makeId) throws IOException, NoSuchFieldException, IllegalAccessException {
        return getListFromJson(this.CAR_API_URL + "/models?year=2020" +
                "&make_id=" + makeId, "data",
                new TypeReference<List<Model>>() {
        },"name");
    }

    @Override
    public List<Engine> getCarEngine(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException {
        return getListFromJson(this.CAR_API_URL + "/engines?year=2020" +
                "&make_model_id=" + makeModelId, "data", new TypeReference<List<Engine>>() {
        },null);
    }

    @Override
    public List<Body> getCarBody(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException {
        return getListFromJson(this.CAR_API_URL + "/bodies?year" +
                        "=2020" +
                "&make_model_id=" + makeModelId, "data",
                new TypeReference<List<Body>>() {
        },"type");
    }

    @Override
    public List<InteriorColor> getCarInteriorColor(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException {
        return getListFromJson(this.CAR_API_URL + "/interior-colors" +
                        "?year=2020" +
                        "&make_model_id=" + makeModelId, "data",
                new TypeReference<List<InteriorColor>>() {
                },"name");
    }
    @Override
    public List<ExteriorColor> getCarExteriorColor(Integer makeModelId) throws IOException, NoSuchFieldException, IllegalAccessException {
        return getListFromJson(this.CAR_API_URL + "/exterior-colors" +
                        "?year=2020" +
                        "&make_model_id=" + makeModelId, "data",
                new TypeReference<List<ExteriorColor>>() {
                },"name");
    }

}
