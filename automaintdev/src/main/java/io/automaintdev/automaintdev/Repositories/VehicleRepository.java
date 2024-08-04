package io.automaintdev.automaintdev.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import io.automaintdev.automaintdev.Beans.MaintenanceRecord;
import io.automaintdev.automaintdev.Beans.Vehicle;

@Repository
public class VehicleRepository {
    
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public List<Vehicle> getAllVehicles() {
        String query = "SELECT * FROM Vehicle";
        return jdbc.query(query, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    // Changed year to vyear to avoid the erros i was getting due to SQL not liking the name
    public void insertVehicle(Vehicle v) {
        String query = "INSERT INTO vehicle (make, model, vyear, vin) VALUES (:make, :model, :vyear, :vin)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("make", v.getMake());
        namedParameters.addValue("model", v.getModel());
        namedParameters.addValue("vyear", v.getVyear());
        namedParameters.addValue("vin", v.getVin());

        jdbc.update(query, namedParameters);
    }

    public Vehicle getVehicleById(Long id) {
        String query = "SELECT * FROM vehicle WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(Vehicle.class));
    }

    public List<MaintenanceRecord> getMaintenanceRecordsByVehicleId(Long vehicleId) {
        String query = "SELECT * FROM maintenance_record WHERE vehicle_id = :vehicle_id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("vehicle_id", vehicleId);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(MaintenanceRecord.class));
    }

    public void deleteVehicle(Long vehicleId) {
        String deleteMaintenanceRecordsQuery = "DELETE FROM maintenance_record WHERE vehicle_id = :vehicleId";
        MapSqlParameterSource maintenanceParams = new MapSqlParameterSource();
        maintenanceParams.addValue("vehicleId", vehicleId);
        jdbc.update(deleteMaintenanceRecordsQuery, maintenanceParams);

        String deleteVehicleQuery = "DELETE FROM vehicle WHERE id = :vehicleId";
        MapSqlParameterSource vehicleParams = new MapSqlParameterSource();
        vehicleParams.addValue("vehicleId", vehicleId);
        jdbc.update(deleteVehicleQuery, vehicleParams);
    }

    public void deleteRecord(Long id) {
        String query = "DELETE FROM maintenance_record WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        jdbc.update(query, namedParameters);
    }

    public void updateVehicle(Vehicle v) {
        String query = "UPDATE vehicle SET make = :make, model = :model, vyear = :vyear, vin = :vin WHERE id = :id";
        
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", v.getId());
        namedParameters.addValue("make", v.getMake());
        namedParameters.addValue("model", v.getModel());
        namedParameters.addValue("vyear", v.getVyear());
        namedParameters.addValue("vin", v.getVin());
        
        jdbc.update(query, namedParameters);
    }

    public void insertRecord(MaintenanceRecord r) {
        String query = "INSERT INTO maintenance_record (vehicle_id, date, description, mileage) VALUES (:vehicleId, :date, :description, :mileage)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("vehicleId", r.getVehicleId());
        namedParameters.addValue("date", r.getDate());
        namedParameters.addValue("description", r.getDescription());
        namedParameters.addValue("mileage", r.getMileage());

        jdbc.update(query, namedParameters);
    }

    public MaintenanceRecord getRecordById(Long id) {
        String query = "SELECT * FROM maintenance_record WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(MaintenanceRecord.class));
    }

    public void updateRecord(MaintenanceRecord r) {
        String query = "UPDATE maintenance_record SET date = :date, description = :description, mileage = :mileage WHERE id = :id";
        
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", r.getId());
        namedParameters.addValue("vehicleId", r.getVehicleId());
        namedParameters.addValue("date", r.getDate());
        namedParameters.addValue("description", r.getDescription());
        namedParameters.addValue("mileage", r.getMileage());
        
        jdbc.update(query, namedParameters);
    }

}
