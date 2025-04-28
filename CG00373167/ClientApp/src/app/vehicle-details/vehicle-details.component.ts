import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.scss'],
  imports: [ FormsModule ]
})
export class VehicleDetailsComponent implements OnInit {
  vehicle: any = null; // This will hold the vehicle details passed from the previous page

  constructor(private router: Router) {
    // Retrieve the vehicle data from the navigation state
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as { vehicle: any };

    // Populate the vehicle details from previous page or if no data passed then initialize as empty object
    this.vehicle = state?.vehicle || {}; 
  }

  ngOnInit(): void {}

  updateMechanic(): void {
    
  }

  navigateHome(): void {
    this.router.navigate(['/vehicles']); // Navigate back to the main page
  }
}