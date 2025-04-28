/**
 * Vehicle interface represents the structure of a vehicle object.
 * This includes nested objects for owner, mechanic, and garage.
 * Ensures type safety and consistent structure when working with vehicle data.
 */
export interface Vehicle {
    reg: string;
    make: string;
    model: string;
    owner: {
      cid: string;
      name: string;
    };
    mechanic: {
      mid: string;
      name: string;
      salary: number;
      garage: {
        gid: string;
        location: string;
        budget: number;
      };
    };
  }