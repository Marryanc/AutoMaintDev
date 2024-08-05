package io.automaintdev.automaintdev.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.automaintdev.automaintdev.Beans.CarSpecs;
import io.automaintdev.automaintdev.Services.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;
    
    // This is the restful response to get the info from the vin returning a car specs entity
    @GetMapping("/vin/{vin}")
    public ResponseEntity<CarSpecs> decodeVin(@PathVariable String vin) {
        CarSpecs carSpecs = carService.decodeVin(vin);
        return ResponseEntity.ok(carSpecs);
    }
}
