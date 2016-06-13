package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import javax.ws.rs.core.MediaType;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

public class PersonServiceTest {
	
	private static final String PERSON_FIRST_NAME = "Jasiu";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
    }
	
	@Test
	public void clearPersons() {
		delete("/person/deletePersons/").then().assertThat().statusCode(200);
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Person.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
	}
	
	@Test
	public void clearCars() {
		delete("/person/deleteCars/").then().assertThat().statusCode(200);
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Car.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
	}
	
	@Test
	public void addPersons(){		
		
		delete("/person/deletePersons/").then().assertThat().statusCode(200);
		Person person = new Person(1,PERSON_FIRST_NAME, 1976);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(person)
		.when()
	    	.post("/person/add").then().assertThat().statusCode(201);	
			Person rPerson = get("/person/1").as(Person.class);
			assertThat(PERSON_FIRST_NAME, equalToIgnoringCase(rPerson.getFirstName()));
	}
	
	@Test
	public void addCars(){		
		delete("/person/deleteCars/").then().assertThat().statusCode(200);
		Car car = new Car(2,"Fiat", 1976);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(car)
		.when()
	    	.post("/person/addCarWithId").then().assertThat().statusCode(201);	
			Car rCar = get("/person/getCar/2").as(Car.class);
			assertThat("Fiat", equalToIgnoringCase(rCar.getModel()));
	}
	
	@Test
	public void getAllPersons(){
		delete("/person/deleteCars/").then().assertThat().statusCode(200);
		Person person1 = new Person(1,"Jeden", 1971);
		Person person2 = new Person(2,"Dwa", 1972);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(person1)
		.when()
			.post("/person/add").then().assertThat().statusCode(201);
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(person2)
		.when()
			.post("/person/add").then().assertThat().statusCode(201);

		given()
		.when()
			.get("/person/getAll")
		.then()
			.body("person[0].id", equalTo("1"))
			.body("person[0].firstName", equalTo("Jeden"))
			.body("person[0].yob", equalTo("1971"))
			.body("person[1].id", equalTo("2"))
			.body("person[1].firstName",equalTo("Dwa"))
			.body("person[1].yob", equalTo("1972"))
		
			.body("person.id", hasItems("1","2"));
	}
	
	@Test
	public void getCarWithOwner(){
		delete("/person/deleteCars/").then().assertThat().statusCode(200);
		delete("/person/deletePersons/").then().assertThat().statusCode(200);
		Person person1 = new Person(1,"Jeden", 1971);
		Car car1 = new Car(1,"Fiat",1999);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(person1)
		.when()
			.post("/person/add").then().assertThat().statusCode(201);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(car1)
		.when()
			.post("/person/addCarWithId").then().assertThat().statusCode(201);
		
		given()
		.when()
			.get("/person/car/1")
		.then()
			.body("id", equalTo("1"))
			.body("model", equalTo("Fiat"))
			.body("yop", equalTo("1999"))
			.body("owner.id", equalTo("1"))
			.body("owner.firstName", equalTo("Jeden"))
			.body("owner.yob", equalTo("1971"));
	}
	@Ignore
	@Test
	public void getCarWithOwnerStmt(){
		delete("/person/deleteCars/").then().assertThat().statusCode(200);
		Person person1 = new Person(1,"Jeden", 1971);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(person1)
		.when()
			.post("/person/add").then().assertThat().statusCode(201);
		
		Car car1 = new Car(1,"Fiat", 1971);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(car1)
		.when()
			.post("/person/addCarWithId").then().assertThat().statusCode(201);
		
	}
	
	

}
