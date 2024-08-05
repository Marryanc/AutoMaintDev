package io.automaintdev.automaintdev.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.automaintdev.automaintdev.Beans.CarSpecs;

@Service
public class CarService {

    // Setup this logger in order to debug the api 
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);
    private final RestTemplate restTemplate;

    @Autowired
    public CarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // This gets the data from the api and stores the json response into a carSpecs object
    public CarSpecs decodeVin(String vin) {
        String url = "https://carapi.app/api/vin/" + vin + "?verbose=no&all_trims=no";
        logger.info("Fetching VIN specs from URL: {}", url);
        CarSpecs carSpecs = restTemplate.getForObject(url, CarSpecs.class);
        logger.info("Received response: {}", carSpecs);
        return carSpecs;
        // https://carapi.app/api/vin/1HGCM82633A123456?verbose=no&all_trims=no
    }
}

