package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Vehicle;

/**
 * Repository interface for Vehicle entities.
 * Provides methods for interacting with the database.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	/**
	 * Finds all Vehicles with the specified make.
	 *
	 * @param make the make of the vehicles to retrieve
	 * @return a list of matching Vehicle entities
	 */
	List<Vehicle> findByMake(String make);
	
	/**
     * Checks if a Vehicle with the given reg value exists in the database.
     *
     * @param reg the reg value to check
     * @return true if a Vehicle with the given reg exists, false otherwise
     */
    boolean existsByReg(String reg);
    
    /**
     * Finds a Vehicle by its reg value.
     *
     * @param reg the reg value of the vehicle
     * @return an Optional containing the Vehicle if found, or empty otherwise
     */
    Optional<Vehicle> findByReg(String reg);
}
