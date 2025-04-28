import { Component, OnInit } from '@angular/core';
import { VehicleService } from '../services/vehicle.service'; 
import { Vehicle } from '../models/vehicle.model'; 
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

/**
 * VehiclesComponent is responsible for displaying a list of vehicles and handling user interactions.
 */
@Component({
  selector: 'app-vehicles',
  standalone: true, 
  templateUrl: './vehicles.component.html', 
  styleUrls: ['./vehicles.component.scss'], 
  providers: [VehicleService], // Provide the VehicleService directly in this component
  imports: [CommonModule],
})
export class VehiclesComponent implements OnInit {
  // Array to hold vehicles fetched from the backend
  vehicles: Vehicle[] = [];
  selectedVehicle: Vehicle | null = null;

  
  constructor(private vehicleService: VehicleService, private router: Router) {}

  /**
   * Lifecycle hook that is called after the component is initialized.
   * Fetches the list of vehicles from the backend.
   */
  ngOnInit(): void {
    this.loadVehicles();
  }

  /**
   * Calls the service to fetch all vehicles and assigns them to the `vehicles` array.
   */
  loadVehicles(): void {
    this.vehicleService.getVehicles().subscribe((data: Vehicle[]) => {
      this.vehicles = data; // Assign the data to the local array
    });
  }

  /**
   * Handles the click event for the "Update" button.
   * @param vehicle The vehicle object for which the button was clicked.
   */
  updateVehicle(vehicle: Vehicle): void {
    this.router.navigate(['/vehicleDetails'], { state: { vehicle } });
  }
}