package com.example.demo.dtos;

import com.example.demo.models.Vehicle;

/**
 * Data Transfer Object (DTO) for Vehicle.
 * Represents the structure of the JSON response for the Vehicle entity,
 * including nested objects for Owner, Mechanic, and Garage.
 */
public class VehicleDTO {

    private String reg;
    private String make;
    private String model;
    private OwnerDTO owner;
    private MechanicDTO mechanic;

    /**
     * Converts a Vehicle entity into a VehicleDTO.
     *
     * @param vehicle the Vehicle entity to convert
     * @return a VehicleDTO representing the given Vehicle entity
     */
    public static VehicleDTO fromEntity(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.reg = vehicle.getReg();
        dto.make = vehicle.getMake();
        dto.model = vehicle.getModel();
        dto.owner = new OwnerDTO(vehicle.getOwner().getCid(), vehicle.getOwner().getName());
        dto.mechanic = new MechanicDTO(
            vehicle.getMechanic().getMid(),
            vehicle.getMechanic().getName(),
            vehicle.getMechanic().getSalary(),
            new GarageDTO(
                vehicle.getMechanic().getGarage().getGid(),
                vehicle.getMechanic().getGarage().getLocation(),
                vehicle.getMechanic().getGarage().getBudget()
            )
        );
        return dto;
    }

    // Getters
    public String getReg() {
        return reg;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public MechanicDTO getMechanic() {
        return mechanic;
    }

    /**
     * Nested DTO for Owner.
     * Represents the owner of the vehicle with their CID and name.
     */
    public static class OwnerDTO {
        private String cid;
        private String name;

        /**
         * Constructs an OwnerDTO.
         *
         * @param cid  the unique identifier of the owner
         * @param name the name of the owner
         */
        public OwnerDTO(String cid, String name) {
            this.cid = cid;
            this.name = name;
        }

        public String getCid() {
            return cid;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Nested DTO for Mechanic.
     * Represents the mechanic responsible for the vehicle, including their details and associated garage.
     */
    public static class MechanicDTO {
        private String mid;
        private String name;
        private Double salary;
        private GarageDTO garage;

        /**
         * Constructs a MechanicDTO.
         *
         * @param mid    the unique identifier of the mechanic
         * @param name   the name of the mechanic
         * @param salary the salary of the mechanic
         * @param garage the garage associated with the mechanic
         */
        public MechanicDTO(String mid, String name, Double salary, GarageDTO garage) {
            this.mid = mid;
            this.name = name;
            this.salary = salary;
            this.garage = garage;
        }

        public String getMid() {
            return mid;
        }

        public String getName() {
            return name;
        }

        public Double getSalary() {
            return salary;
        }

        public GarageDTO getGarage() {
            return garage;
        }
    }

    /**
     * Nested DTO for Garage.
     * Represents the garage associated with the mechanic, including its details.
     */
    public static class GarageDTO {
        private String gid;
        private String location;
        private Integer budget;

        /**
         * Constructs a GarageDTO.
         *
         * @param gid      the unique identifier of the garage
         * @param location the location of the garage
         * @param budget   the budget of the garage
         */
        public GarageDTO(String gid, String location, Integer budget) {
            this.gid = gid;
            this.location = location;
            this.budget = budget;
        }

        public String getGid() {
            return gid;
        }

        public String getLocation() {
            return location;
        }

        public Integer getBudget() {
            return budget;
        }
    }
}