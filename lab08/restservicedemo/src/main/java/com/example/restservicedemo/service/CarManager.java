package com.example.restservicedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.restservicedemo.domain.Car;
import com.example.restservicedemo.domain.Person;

public class CarManager {
	private Connection connection;

	private static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	private static final String CREATE_TABLE_CAR = "CREATE TABLE Car(c_id bigint GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1), "
			+ "model varchar(20), yop integer, owner_id int FOREIGN KEY REFERENCES Person(p_id))";
	
	private PreparedStatement addCarStmt;
	private PreparedStatement deleteAllCarsStmt;
	private PreparedStatement getAllCarsStmt;
	private PreparedStatement getCarByIdStmt;
	private PreparedStatement getAllCarsByOwnerIdStmt;
	private PreparedStatement sellCarStmt;
	private PreparedStatement dropCarTableStmt;

	private Statement statement;
	
	private PersonManager pm = new PersonManager();

	public CarManager() {
		try {
			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Car".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(CREATE_TABLE_CAR);

			addCarStmt = connection
					.prepareStatement("INSERT INTO Car (model, yop) VALUES (?, ?, ?)");
			deleteAllCarsStmt = connection
					.prepareStatement("DELETE FROM Car");
			getAllCarsStmt = connection
					.prepareStatement("SELECT c_id, model, yop, owner_id FROM Car");
			getCarByIdStmt = connection
					.prepareStatement("SELECT c_id, model, yop, owner_id FROM Car WHERE c_id = ?");
			getAllCarsByOwnerIdStmt = connection
					.prepareStatement("SELECT c_id, model, yop, owner_id, name, yob "
							+ "FROM Car INNER JOIN Person ON owner_id = p_id WHERE owner_id = ?");
			sellCarStmt = connection
					.prepareStatement("UPDATE Car SET owner_id = ? WHERE c_id = ?");
			dropCarTableStmt = connection
					.prepareStatement("DROP TABLE Car");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	public void clearCars() {
		try {
			deleteAllCarsStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int addCar(Car car) {
		int count = 0;
		try {
			addCarStmt.setString(2, car.getModel());
			addCarStmt.setInt(3, car.getYop());

			count = addCarStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Car> getAllCars() {
		List<Car> cars = new ArrayList<Car>();

		try {
			ResultSet rs = getAllCarsStmt.executeQuery();

			while (rs.next()) {
				Car c = new Car();
				c.setId(rs.getLong("c_id"));
				c.setModel(rs.getString("model"));
				c.setYop(rs.getInt("yop"));
				if (rs.getLong("owner_id") != 0)
					c.setOwner(pm.getPerson(rs.getLong("owner_id")));
				cars.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cars;
	}

	public Car getCar(Long id) {
		Car c = new Car();
		try {
			getCarByIdStmt.setLong(1, id);
			ResultSet rs = getCarByIdStmt.executeQuery();

			while (rs.next()) {
				c.setId(rs.getLong("c_id"));
				c.setModel(rs.getString("model"));
				c.setYop(rs.getInt("yop"));
				if (rs.getLong("owner_id") != 0)
					c.setOwner(pm.getPerson(rs.getLong("owner_id")));
				break;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}
	
	public List<Car> getAllCarsByOwner(Long owner_id) {
		List<Car> cars = new ArrayList<Car>();

		try {
			getAllCarsByOwnerIdStmt.setLong(1, owner_id);
			ResultSet rs = getAllCarsByOwnerIdStmt.executeQuery();

			while (rs.next()) {
				Car c = new Car();
				
				c.setId(rs.getInt("c_id"));
				c.setYop(rs.getInt("yop"));
				c.setOwner(pm.getPerson(rs.getLong("owner_id")));
				cars.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cars;
	}
	
	public int sellCar(Car car, Person person) {
		int count = 0;
		try {
			sellCarStmt.setLong(1, person.getId());
			sellCarStmt.setLong(2, car.getId());

			count = sellCarStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void deleteCarTable() {
		try {
			dropCarTableStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}