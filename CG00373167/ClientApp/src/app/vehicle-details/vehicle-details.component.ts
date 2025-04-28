import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { VehicleService } from '../services/vehicle.service';
import { Vehicle } from '../models/vehicle.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.scss'],
  standalone: true,
  imports: [ FormsModule, CommonModule ],
})
export class VehicleDetailsComponent implements OnInit {
  // Vehicle object passed by nav state
  vehicle!: Vehicle;
  // Two way data bind to mid input
  updatedMechanicMid: string = '';

  // For error 
  errorMessage: string | null = null;

  constructor(
    private router: Router,
    private vehicleService: VehicleService
  ) {

    // Retrieve the vehicle data from the router's navigation extras
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as { vehicle: Vehicle };
    if (!state?.vehicle) {
      // No vehicle passed—go back
      this.navigateHome();
    }
    this.vehicle = state.vehicle;
    this.updatedMechanicMid = this.vehicle.mechanic.mid;
  }

  ngOnInit(): void {}

   /**
   * Called when you click update. 
   * Sends the new MID to the backend, then navigates back on success.
   */
  updateMechanic(): void {
    const payload = { mid: this.updatedMechanicMid.trim() };

    this.vehicleService
      .updateMechanic(this.vehicle.reg, payload)
      .subscribe({
        next: () => this.navigateHome(),
        error: err => {
          // Redirect to /error, passing status and message
          const status = err.status || 500;
          const message = err.message || 'Unknown error';
          this.router.navigate(['/error'], { state: { status, message } });
      }});
  }


  /** Delete the current vehicle, on success go home, on failure show error banner */
  deleteVehicle(): void {
    this.errorMessage = null;
    this.vehicleService
      .deleteVehicle(this.vehicle.reg)
      .subscribe({
        next: () => this.navigateHome(),
        error: err => {
          this.errorMessage =
            err.error?.message ||
            err.message ||
            `Error ${err.status}`;
        }
      });
  }

  navigateHome(): void {
    this.router.navigate(['/vehicles']);
  }
}