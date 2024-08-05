package io.automaintdev.automaintdev.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class vinController {
    
    @GetMapping("/api/cars/vin")
    public String vin() {
        return "vinSpecs";
    }
}
