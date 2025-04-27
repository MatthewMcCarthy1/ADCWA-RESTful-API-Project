package com.example.demo.services;

import com.example.demo.models.Mechanic;
import com.example.demo.repositories.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for handling business logic related to Mechanics.
 */
@Service
public class MechanicService {

	/** Repository for accessing mechanic data. 
	 */
    @Autowired
    private MechanicRepository mechanicRepository;

    /**
     * Deletes a Mechanic from the database by its mid.
     *
     * @param mid the mid of the Mechanic to delete
     * @throws IllegalArgumentException if the Mechanic does not exist
     */
    public void deleteMechanic(String mid) {
    	// Check if mechanic exists and throw exception if not found
        Mechanic mechanic = mechanicRepository.findByMid(mid)
                .orElseThrow(() -> new IllegalArgumentException("Mechanic with mid '" + mid + "' doesn't exist."));
        
        if(mechanic.getVehicles() != null && !mechanic.getVehicles().isEmpty()) {
        	throw new IllegalArgumentException("Mechanic with mid '" + mid + "' cannot be deleted because they are servicing vehicles");
        }

        // Delete the Mechanic
        mechanicRepository.delete(mechanic);
    }
}