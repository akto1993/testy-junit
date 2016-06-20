package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

public class BLTest {

	PersonManager pm = new PersonManager();

	@Test
	public void checkCarAdding() {

		Car c = new Car();
		c.setModel("Corsa");
		c.setYop(2003);

		assertEquals(1, pm.addCar(c));
	}

	@Test
	public void checkAddPerson(){
		Person p = new Person();
		p.setFirstName("Grazyna");
		p.setYob(1967);

		assertEquals(1, pm.addPerson(p));
	}

	@Test
	public void checkGetPersonWithCars(){
		pm.clearCars();
		pm.clearPersons();

		Car c1 = new Car();
		c1.setModel("Corsa");
		c1.setYop(2003);

		Car c2 = new Car();
		c2.setModel("Mazda 6");
		c2.setYop(1999);

		Car c3 = new Car();
		c2.setModel("Ford Focus");
		c2.setYop(1989);

		assertEquals(1, pm.addCar(c1));
		assertEquals(1, pm.addCar(c2));
		assertEquals(1, pm.addCar(c3));

		List<Car> cars = pm.getAllCars();

		assertTrue(cars.size() > 0);

		Car carToSell1 = cars.get(0);
		Car carToSell2 = cars.get(1);
		Car carToSell3 = cars.get(2);

		Person p1 = new Person();
		p1.setFirstName("Tomasz");
		p1.setYob(1978);
		assertEquals(1, pm.addPerson(p1));

		List<Person> persons = pm.getAllPersons();

		assertTrue(persons.size() >= 1);

		Person owner = persons.get(0);
		pm.sellCar(carToSell1, owner);
		pm.sellCar(carToSell2, owner);
		pm.sellCar(carToSell3, owner);
		Map<Person, List<Car>> res = pm.getPersonWithCar();

		assertTrue(res.size() >= 1);
	}

	@Test
	public void checkSell() {

		pm.clearCars();
		pm.clearPersons();

		Car c1 = new Car();
		c1.setModel("Corsa");
		c1.setYop(2003);

		Car c2 = new Car();
		c2.setModel("Mazda 6");
		c2.setYop(1999);

		assertEquals(1, pm.addCar(c1));
		assertEquals(1, pm.addCar(c2));

		List<Car> cars = pm.getAllCars();

		assertTrue(cars.size() > 0);

		Car carToSell = cars.get(1);

		Person p1 = new Person();
		p1.setFirstName("Tomasz");
		p1.setYob(1978);

		Person p2 = new Person();
		p2.setFirstName("Grzegorz");
		p2.setYob(1978);

		assertEquals(1, pm.addPerson(p1));
		assertEquals(1, pm.addPerson(p2));

		List<Person> persons = pm.getAllPersons();

		assertTrue(persons.size() > 1);

		Person owner = persons.get(1);


		pm.sellCar(carToSell, owner);

		Car rCar = pm.getCarWithOwner(carToSell);

		assertEquals(owner.getFirstName(), rCar.getOwner().getFirstName());
	}

	@Test
	public void checkGetAll() {

		pm.clearCars();
		pm.clearPersons();

		List<Person> persons = pm.getAllPersons();
		List<Car> cars = pm.getAllCars();

		assertEquals(0, persons.size());
		assertEquals(0, cars.size());

		Car c = new Car();
		c.setModel("Corsa");
		c.setYop(2003);

		assertEquals(1, pm.addCar(c));

		Person p = new Person();
		p.setFirstName("Grazyna");
		p.setYob(1967);

		assertEquals(1, pm.addPerson(p));

		persons = pm.getAllPersons();
		cars = pm.getAllCars();

		assertEquals(1, persons.size());
		assertEquals(1, cars.size());

	}

}
