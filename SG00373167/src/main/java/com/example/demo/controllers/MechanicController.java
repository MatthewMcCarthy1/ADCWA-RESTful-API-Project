package com.example.demo.controllers;

import com.example.demo.services.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for managing Mechanic-related endpoints.
 */
@RestController
public class MechanicController {

	/**
	 * Service layer to handle business logic for mechanics.
	 */
    @Autowired
    private MechanicService mechanicService;

    /**
     * Deletes a Mechanic from the database by its mid.
     *
     * @param mid the mid of the Mechanic to delete
     * @return a ResponseEntity with HTTP status 200 if successful
     */
    @DeleteMapping("/api/mechanic/{mid}")
    public ResponseEntity<String> deleteMechanic(@PathVariable String mid) {
        mechanicService.deleteMechanic(mid);
        return ResponseEntity.ok("Mechanic with mid '" + mid + "' deleted successfully.");
    }
}