package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.service.PersonManager;

@Path("cars")
public class CarFakeRESTService {	
	
	private PersonManager pm = new PersonManager();
	
	@GET
	@Path("/{carId}")
	@Produces("application/json")
	public Car getCar(@PathParam("carId") Long id){
		return new Car(1L, "Opel", 2005);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCar(Car car) {
		
		pm.addCar(car);
		return Response.status(201).entity("Car").build(); 
	}

}