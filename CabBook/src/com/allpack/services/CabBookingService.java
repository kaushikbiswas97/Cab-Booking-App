package com.allpack.services;

import com.allpack.Gender;
import com.allpack.exceptions.CreateExceptions;
import com.allpack.exceptions.DriverNotAvailableException;
import com.allpack.models.Driver;
import com.allpack.models.Location;
import com.allpack.models.Rider;
import com.allpack.models.Vehicle;

public class CabBookingService {
	 private static CabBookingService cabBookingService = null;

	    private CabBookingService(){

	    }

	    public static CabBookingService getInstance(){
	        if(cabBookingService == null){
	            cabBookingService = new CabBookingService();
	        }
	        return cabBookingService;
	    }

	    private UserService userService = UserService.getInstance();

	    private RiderService rideService = RiderService.getInstance();

	    public Driver registerDriver(String name, int id, Gender gender, Vehicle vehicle, boolean available) throws CreateExceptions{
	        return userService.registerDriver(name, id, gender, vehicle, available);
	    }
	    public Rider registerRider(String name, int id, Gender gender, Location fromLocation, Location toLocation) throws CreateExceptions {
	        return userService.registerRider(name, id, gender, fromLocation, toLocation);
	    }

	    public Driver bookRide(int riderId, Location fromLocation, Location toLocation) throws DriverNotAvailableException {
	        return rideService.bookRide(riderId, fromLocation, toLocation);
	    }
}
