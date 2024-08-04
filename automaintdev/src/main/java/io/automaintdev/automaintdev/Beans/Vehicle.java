package io.automaintdev.automaintdev.Beans;

import lombok.Data;

@Data
public class Vehicle {
    private Long id;
    private String make;
    private String model;
    private int vyear;
    private String vin;
}
