package com.example.demo.dtos;

/**
 * Data Transfer Object (DTO) for creating a new Vehicle.
 * Represents the structure of the incoming request
 */
public class VehiclePersistDTO {
	// Must be provided 
	private String reg;
    private String make;
    private String model;
    
 // Getters and setters
    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    // Not allowed
    private String id;
    private String owner;
    private String mechanic;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMechanic() {
		return mechanic;
	}

	public void setMechanic(String mechanic) {
		this.mechanic = mechanic;
	}

}
