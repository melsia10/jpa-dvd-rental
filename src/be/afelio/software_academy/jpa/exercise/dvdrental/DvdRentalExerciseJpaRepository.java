package be.afelio.software_academy.jpa.exercise.dvdrental;

import java.util.Date;
import java.util.List;

import be.afelio.software_academy.jpa.exercise.dvdrental.beans.*;

public interface DvdRentalExerciseJpaRepository {

	Country findOneCountryByName(String name);
	List<? extends City> findAllCitiesByCountryName(String name);
	List<? extends Address> findAllStoreAddressesByCountryName(String name);
	City createCity(String cityName, String countryName);
	boolean deleteCity(String cityName, String countryName);
	List<? extends Rental> findAllRentalsByFilmTitle(String title);
	boolean updateRentalReturnDate(int rentalId, Date returnDate);
	Rental createAndStoreRental(String filmTitle, String storeAddress, String customerEmail, String staffUsername);
}
