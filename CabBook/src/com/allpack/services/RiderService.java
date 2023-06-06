package com.allpack.services;
import com.allpack.exceptions.DriverNotAvailableException;
import com.allpack.models.Driver;
import com.allpack.models.Location;
import com.allpack.models.Ride;
import com.allpack.models.Rider;

import java.util.ArrayList;
import java.util.List;

public class RiderService {
	 private static RiderService riderService = null;

	    private UserService userService = UserService.getInstance();

	    private static Integer MAX_DISTANCE = 4;
	    private RiderService(){

	    }

	    public static RiderService getInstance(){
	        if(riderService == null){
	            riderService = new RiderService();
	        }
	        return riderService;
	    }

	    public Driver bookRide(int riderId, Location fromLocation, Location toLocation) throws DriverNotAvailableException{
	        Rider rider = userService.riderMap.get(riderId);
	        List<Driver> driverList = getAllAvailableDrivers(fromLocation);
	        if(driverList.isEmpty()){
	            throw new DriverNotAvailableException("Not driver found");
	        }
	        driverList.get(0).setAvailable(false);
	        Ride ride = new Ride(driverList.get(0), rider, fromLocation, toLocation);
	        rider.getRideList().add(ride);
	        return driverList.get(0);
	    }

	    public List<Driver> getAllAvailableDrivers(Location location) {
	        List<Driver> driverList = new ArrayList<Driver>();
	        for(Driver driver : userService.getDriverMap().values()){
	            if(driver.isAvailable() && distance(driver.getVehicle().getLocation(), location) <= MAX_DISTANCE){
	                driverList.add(driver);
	            }
	        }
	        return driverList;
	    }

	    public Integer distance(Location l1, Location l2){
	        return Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY());
	    }

}
