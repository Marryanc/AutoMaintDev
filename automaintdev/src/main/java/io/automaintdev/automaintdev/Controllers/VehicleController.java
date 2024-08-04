package io.automaintdev.automaintdev.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.automaintdev.automaintdev.Beans.MaintenanceRecord;
import io.automaintdev.automaintdev.Beans.Vehicle;
import io.automaintdev.automaintdev.Repositories.VehicleRepository;

@Controller
public class VehicleController {

    @Autowired
    private VehicleRepository da;
    
    @GetMapping("/")
    public String vehicle(Model model) {
        List<Vehicle> vehicles = da.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle";
    }

    @GetMapping("/vehicle/add")
    public String addVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "add_vehicle";
    }

    @PostMapping("/vehicle/add")
    public String addVehicleSubmit(@ModelAttribute Vehicle vehicle) {
        da.insertVehicle(vehicle);
        return "redirect:/";
    }

    @GetMapping("/vehicle/{id}")
    public String vehicleDetails(@PathVariable Long id, Model model) {
        Vehicle vehicle = da.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("maintenanceRecords", da.getMaintenanceRecordsByVehicleId(id));
        return "vehicle_details";
    }

    @GetMapping("/vehicle/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", da.getVehicleById(id));
        return "edit_vehicle";
    }

        @PostMapping("/vehicle/edit")
        public String editVehicleSubmit(@ModelAttribute Vehicle vehicle) {
            da.updateVehicle(vehicle);
            return "redirect:/";
        }

        @GetMapping("/vehicle/delete/{id}")
        public String deleteVehicle(@PathVariable Long id) {
            da.deleteVehicle(id);
            return "redirect:/";
        }

    @GetMapping("/maintenace/add/{vehicleId}")
    public String addMaintenanceForm(@PathVariable Long vehicleId, Model model) {
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setVehicleId(vehicleId);
        model.addAttribute("maintenanceRecord", maintenanceRecord);
        return "add_maintenace";
    }

    @PostMapping("/maintenace/add")
    public String addMaintenanceSubmit(@ModelAttribute MaintenanceRecord maintenanceRecord) {
        da.insertRecord(maintenanceRecord);
        return "redirect:/vehicle/" + maintenanceRecord.getVehicleId();
    }

    @GetMapping("/maintenace/delete/{id}")
    public String deleteRecord(@PathVariable Long id) {
        MaintenanceRecord recrod = da.getRecordById(id);
        Long vehicleId = recrod.getVehicleId();
        da.deleteRecord(id);
        // using concatanation with the vehicle id number to display the vehicle page after the record is deleted
        return "redirect:/vehicle/" + vehicleId;
    }

    @GetMapping("/maintenace/edit/{id}")
    public String editMaintenanceForm(@PathVariable Long id, Model model) {
        model.addAttribute("maintenanceRecord", da.getRecordById(id));
        return "edit_maintenance";
    }

    @PostMapping("/maintenace/edit")
    public String editMaintenanceSubmit(@ModelAttribute MaintenanceRecord record) {
        da.updateRecord(record);
        return "redirect:/vehicle/" + record.getVehicleId();
    }
}
