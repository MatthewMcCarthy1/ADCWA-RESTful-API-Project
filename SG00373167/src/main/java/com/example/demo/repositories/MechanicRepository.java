package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Mechanic;

/**
 * Repository interface for Mechanic entities.
 * Provides methods for interacting with the database.
 */
@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {

    /**
     * Finds a Mechanic by its mid value.
     *
     * @param mid the mid value of the mechanic
     * @return an Optional containing the Mechanic if found, or empty otherwise
     */
    Optional<Mechanic> findByMid(String mid);
    
    
}
