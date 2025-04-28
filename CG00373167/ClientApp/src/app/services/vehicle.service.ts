import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs'; 
import { Vehicle } from '../models/vehicle.model'; 

@Injectable({
  providedIn: 'root' 
})
export class VehicleService {
  /**
   * URL of the backend API for fetching vehicle data.
   */
  private apiUrl = 'http://localhost:8080/api/vehicle';

  constructor(private http: HttpClient) {}

  /**
   * Fetches all vehicles from the backend API.
   * @returns An Observable of Vehicle array containing the details of all vehicles.
   */
  getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(`${this.apiUrl}/all`); // Executes a HTTP GET request
  }

  
}