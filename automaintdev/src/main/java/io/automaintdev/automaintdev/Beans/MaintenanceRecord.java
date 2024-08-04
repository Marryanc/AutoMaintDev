package io.automaintdev.automaintdev.Beans;

import java.sql.Date;

import lombok.Data;

@Data
public class MaintenanceRecord {
    private Long id;
    private Long vehicleId;
    private Date date;
    private String description;
    private int mileage;
}
