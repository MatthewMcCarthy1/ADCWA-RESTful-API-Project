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

  /**
   * Sends a PUT request to update the mechanic assigned to a specific vehicle.
   *
   * @param reg      The registration identifier of the vehicle to update
   * @param mechanic An object containing the new mechanic MID to set
   * @returns        Observable that emits the updated Vehicle on success
   */
  updateMechanic(reg: string, mechanic: { mid: string }): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${reg}`, mechanic);
  }


  // INNOVATION
  deleteVehicle(reg: string) {
    return this.http.delete<void>(`${this.apiUrl}/${reg}`);
  }
}