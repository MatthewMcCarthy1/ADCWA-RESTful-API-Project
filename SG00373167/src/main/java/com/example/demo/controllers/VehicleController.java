package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

import com.example.demo.dtos.VehicleDTO;
import com.example.demo.dtos.VehiclePersistDTO;
import com.example.demo.models.Vehicle;
import com.example.demo.services.VehicleService;

/**
 * REST Controller for managing Vehicle-related endpoints.
 * Handles HTTP requests and responses for Vehicle operations.
 */
@RestController
public class VehicleController {

	/**
	 * Service layer to handle business logic for vehicles.
	 */
	@Autowired
	private VehicleService vehicleService;
	
	/**
     * Retrieves all vehicles and their associated details.
     *
     * @return a list of VehicleDTO objects representing all vehicles
     */
	@GetMapping(path = "/api/vehicle/all")
	public List<VehicleDTO> getAllVehicles() {
		return vehicleService.getAllVehicles();
	}
	
	
	
	/**
     * Retrieves all vehicles with the specified make.
     *
     * @param make the make of the vehicle
     * @return a list of VehicleDTO objects representing vehicles with the specified make
     */
    @GetMapping("/api/vehicle")
    public List<VehicleDTO> getVehiclesByMake(@RequestParam String make) {
        return vehicleService.getVehiclesByMake(make);
    }
    
    
    
    /**
     * Persists a new Vehicle to the database.
     *
     * @param vehiclePersistDTO the DTO containing the vehicle details
     * @return a ResponseEntity containing the persisted Vehicle and HTTP status 200
     */
    @PostMapping("/api/vehicle")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody VehiclePersistDTO vehiclePersistDTO) {
        Vehicle createdVehicle = vehicleService.createVehicle(vehiclePersistDTO);
        return ResponseEntity.ok(createdVehicle); // Return 200 OK with the created Vehicle
    }
    
    
    /**
     * Updates the mechanic associated with an existing Vehicle.
     *
     * @param reg the reg value of the vehicle to update
     * @param requestBody a map containing the mid value of the mechanic
     * @return a ResponseEntity containing the updated Vehicle and HTTP status 200
     */
    @PutMapping("/api/vehicle/{reg}")
    public ResponseEntity<Vehicle> updateMechanic(@PathVariable String reg, @RequestBody Map<String, Object> requestBody) {
        // Extract the mid value from the request body
        String mid = (String) requestBody.get("mid");

        // Delegate to the service layer
        Vehicle updatedVehicle = vehicleService.updateMechanic(reg, mid, requestBody);
        return ResponseEntity.ok(updatedVehicle); // Return 200 OK with the updated Vehicle
    }
    	
}
