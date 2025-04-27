package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.example.demo.dtos.VehicleDTO;
import com.example.demo.dtos.VehiclePersistDTO;
import com.example.demo.models.Mechanic;
import com.example.demo.models.Vehicle;
import com.example.demo.repositories.MechanicRepository;
import com.example.demo.repositories.VehicleRepository;

/**
 * Service class for handling business logic related to Vehicles.
 * Interacts with the VehicleRepository to fetch data and map it to DTOs.
 */
@Service
public class VehicleService {
	
	/** 
	 * Repository for accessing vehicle data. 
	 */
	@Autowired
	private VehicleRepository vehicleRepository;
	
	/** 
	 * Repository for accessing mechanic data.
	 */
	@Autowired
	private MechanicRepository mechanicRepository;
	
	/**
     * Retrieves all vehicles from the database and maps them to VehicleDTO objects.
     *
     * @return a list of VehicleDTO objects representing all vehicles
     */
	public List<VehicleDTO> getAllVehicles() {
		return vehicleRepository.findAll()
				.stream()
				.map(VehicleDTO::fromEntity)
				.collect(Collectors.toList());
	}
	
	
	
	/**
     * Retrieves all vehicles with the specified make and maps them to VehicleDTO objects.
     *
     * @param make the make of the vehicle
     * @return a list of VehicleDTO objects representing vehicles with the specified make
     */
    public List<VehicleDTO> getVehiclesByMake(String make) {
        return vehicleRepository.findByMake(make)
                .stream()
                .map(VehicleDTO::fromEntity)
                .collect(Collectors.toList());
    }
    
    
    
    /**
     * Persists a new Vehicle to the database.
     * Validates that restricted attributes (e.g., id, owner, mechanic) are not provided,
     * and required fields (reg, make, model) are present and valid.
     *
     * @param vehiclePersistDTO the DTO containing new vehicle data
     * @return the newly created Vehicle entity
     * @throws IllegalArgumentException if input validation fails
     */
    public Vehicle createVehicle(VehiclePersistDTO vehiclePersistDTO) {
    	StringBuilder errorMessage = new StringBuilder();
    	
    	if (vehiclePersistDTO.getId() != null) {
            errorMessage.append("save.vehicle.id: id must not be provided. ");
        }
        if (vehiclePersistDTO.getOwner() != null) {
            errorMessage.append("save.vehicle.owner: owner must not be provided. ");
        }
        if (vehiclePersistDTO.getMechanic() != null) {
            errorMessage.append("save.vehicle.mechanic: mechanic must not be provided. ");
        }

        // Validate required fields
        if (vehiclePersistDTO.getReg() == null || vehiclePersistDTO.getReg().isEmpty()) {
            errorMessage.append("save.vehicle.reg: reg must be provided. ");
        } else {
            // Only check if reg exists when it's not null or empty
            if (vehicleRepository.existsByReg(vehiclePersistDTO.getReg())) {
                errorMessage.append("Vehicle '" + vehiclePersistDTO.getReg() + "' already exists. ");
            }
        }
        if (vehiclePersistDTO.getMake() == null || vehiclePersistDTO.getMake().isEmpty()) {
            errorMessage.append("save.vehicle.make: make must be provided. ");
        }
        if (vehiclePersistDTO.getModel() == null || vehiclePersistDTO.getModel().isEmpty()) {
            errorMessage.append("save.vehicle.model: model must be provided. ");
        }

        // If there are any validation errors, throw an exception with the combined message
        if (errorMessage.length() > 0) {
            throw new IllegalArgumentException(errorMessage.toString().trim());
        }

        // Map DTO to entity and persist
        Vehicle vehicle = new Vehicle();
        vehicle.setReg(vehiclePersistDTO.getReg());
        vehicle.setMake(vehiclePersistDTO.getMake());
        vehicle.setModel(vehiclePersistDTO.getModel());
        return vehicleRepository.save(vehicle);
    }
    
    
    /**
     * Updates the mechanic associated with an existing Vehicle.
     * Validates that restricted attributes (e.g., id, name, salary, garage, vehicles) are not provided,
     * and ensures that the required field 'mid' is present and valid.
     *
     * @param reg the reg value of the vehicle to update
     * @param mid the mid value of the mechanic to associate with the vehicle
     * @param requestBody the request body containing the mechanic details 
     * @return the updated Vehicle entity with the new mechanic associated
     * @throws IllegalArgumentException if input validation fails
     */
    public Vehicle updateMechanic(String reg, String mid, Map<String, Object> requestBody) {
    	StringBuilder errorMessage = new StringBuilder();
    	
        // Validate restricted attributes
        if (requestBody.containsKey("id")) {
            errorMessage.append("update.vehicle.id: id must not be provided. ");
        }
        if (requestBody.containsKey("name")) {
            errorMessage.append("update.vehicle.name: name must not be provided. ");
        }
        if (requestBody.containsKey("salary")) {
            errorMessage.append("update.vehicle.salary: salary must not be provided. ");
        }
        if (requestBody.containsKey("garage")) {
            errorMessage.append("update.vehicle.garage: garage must not be provided. ");
        }
        if (requestBody.containsKey("vehicles")) {
            errorMessage.append("update.vehicle.vehicles: vehicles must not be provided. ");
        }

        // Validate required field 'mid'
        if (mid == null || mid.isEmpty()) {
            errorMessage.append("update.vehicle.mid: mid must be provided. ");
        }
        
        // If there are any validation errors, throw an exception with the combined message
        if (errorMessage.length() > 0) {
            throw new IllegalArgumentException(errorMessage.toString().trim());
        }

        // Fetch the Vehicle by reg
        Vehicle vehicle = vehicleRepository.findByReg(reg)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle with reg '" + reg + "' doesn't exist."));

        // Fetch the Mechanic by mid
        Mechanic mechanic = mechanicRepository.findByMid(mid)
                .orElseThrow(() -> new IllegalArgumentException("Mechanic with mid '" + mid + "' doesn't exist."));

        // Update the mechanic associated with the vehicle
        vehicle.setMechanic(mechanic);

        // Save the updated vehicle
        return vehicleRepository.save(vehicle);
    }
}
