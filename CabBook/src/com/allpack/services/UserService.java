package com.allpack.services;

import com.allpack.Gender;
import com.allpack.exceptions.CreateExceptions;
import com.allpack.models.*;

import java.util.HashMap;
import java.util.Map;

public class UserService {
	 private static UserService userService = null;

	    private UserService(){

	    }

	    public Map<Integer, Rider> riderMap = new HashMap<Integer, Rider>();

	    public Map<Integer, Driver> driverMap = new HashMap<Integer, Driver>();

	    public Map<Integer, Vehicle> vehicleMap = new HashMap<Integer, Vehicle>();

	    public static UserService getInstance(){
	        if(userService == null){
	            userService = new UserService();
	        }
	        return userService;
	    }

	   public Driver registerDriver(String name, int id, Gender gender, Vehicle v1, boolean available) throws CreateExceptions
	   {
	        Driver driver = new Driver(name, id, gender, v1, available);
	        if(driver == null || driverMap.containsKey(driver.getId())){
	            throw new CreateExceptions("Driver not created !");
	        }
	        driverMap.put(driver.getId(), driver);
	        vehicleMap.put(driver.getVehicle().getModel(), driver.getVehicle());
	        return driver;
	    }

	    public Rider registerRider(String name, int id, Gender gender, Location fromLocation, Location toLocation) throws CreateExceptions
	    {
	        Rider rider = new Rider(name, id, gender, fromLocation, toLocation);
	        if(rider == null || riderMap.containsKey(rider.getId())){
	            throw new CreateExceptions("Rider not created.");
	        }
	        riderMap.put(rider.getId(), rider);
	        return rider;
	    }

	    public Map<Integer, Driver> getDriverMap(){
	        return driverMap;
	    }

	    public Map<Integer, Rider> getRiderMap(){
	        return riderMap;
	    }

	    public Map<Integer, Vehicle> getVehicleMap(){
	        return vehicleMap;
	    }

		
}
