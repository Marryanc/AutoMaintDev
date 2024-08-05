package io.automaintdev.automaintdev.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.automaintdev.automaintdev.Beans.CarSpecs;
import io.automaintdev.automaintdev.Services.CarService;

@Controller
public class vinController {
    
    @Autowired
    private CarService carService;

    // using the carSpecs class to store the info from the api when called
    @GetMapping("/vin")
    public String vinDecoderPage(Model model) {
        model.addAttribute("carSpecs", new CarSpecs());
        return "vinSpecs";
    }

    // Postmapping for the restful api sending the vin to the cars service
    @PostMapping("/vin")
    public String decodeVin(@RequestParam String vin, Model model) {
        CarSpecs carSpecs = carService.decodeVin(vin);
        carSpecs.setVin(vin);
        model.addAttribute("carSpecs", carSpecs);
        return "vinSpecs";
    }
}
