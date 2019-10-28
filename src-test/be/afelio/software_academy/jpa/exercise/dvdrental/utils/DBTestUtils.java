package be.afelio.software_academy.jpa.exercise.dvdrental.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.afelio.software_academy.jpa.exercise.dvdrental.Factory;
import be.afelio.software_academy.pco.jdbc.utils.JdbcTestUtils;

public class DBTestUtils extends JdbcTestUtils {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public DBTestUtils() {
		super(Factory.getDatabaseUrl(), Factory.getDatabaseUser(), Factory.getDatabasePassword());
	}

	public void insertCity(String name, int countryId) {
		update("insert into city(city, country_id) values(?, ?)", name, countryId);
	}
	
	public void deleteCity(String name) {
		update("delete from city where city = ?", name);
	}
	
	public void deleteCountry(String name) {
		update("delete from country where country = ?", name);
	}
	
	public int insertRental(int customerId, int inventoryId, int staffId, Date rentalDate) {
		java.sql.Date sqlDate = new java.sql.Date(rentalDate.getTime());
		update("insert into rental(customer_id, inventory_id, staff_id, rental_date) values(?, ?, ?, ?)", 
				customerId, inventoryId, staffId, sqlDate);
		return queryForObject("select rental_id from rental where customer_id = ? and inventory_id = ? and staff_id = ? and rental_date = ?", 
				Integer.class, customerId, inventoryId, staffId, sqlDate);
	}
	
	public Date selectRentalReturnDate(int rentalId) {
		return queryForObject("select return_date from rental where rental_id = ?", Date.class, rentalId);
	}
	
	public void deleteRental(int id) {
		update("delete from rental where rental_id = ?", id);
	}
	
	public void deleteRental(int inventoryId, int customerId, Date rentalDate) {
		java.sql.Date sqlDate = new java.sql.Date(rentalDate.getTime());
		update("delete from rental where inventory_id = ? and customer_id = ? and rental_date = ?", inventoryId, customerId, sqlDate);
	}
	
	public void deleteRental(Date rentalDate) {
		java.sql.Date sqlDate = new java.sql.Date(rentalDate.getTime());
		update("delete from rental where rental_date = ?", sqlDate);
	}
	
	public Date createDate(int year, int month, int day) {
		try {
			return dateFormat.parse(String.format("%s-%02d-%02d 00:00:00", year, month, day));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
