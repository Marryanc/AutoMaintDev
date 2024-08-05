package io.automaintdev.automaintdev.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.automaintdev.automaintdev.Beans.CarSpecs;
import io.automaintdev.automaintdev.Services.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;
    
    @GetMapping("/vin/{vin}")
    @ResponseBody
    public CarSpecs decodeVin(@PathVariable String vin) {
        return carService.decodeVin(vin);
    }

    @GetMapping("/vinSpecs")
    public String vinSpecs(Model model) {
        model.addAttribute("carSpecs", new CarSpecs());
        return "vinSpecs";
    }
}
