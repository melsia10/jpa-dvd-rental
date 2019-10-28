package be.afelio.software_academy.jpa.exercise.dvdrental;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.*;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Rental;
import be.afelio.software_academy.jpa.exercise.dvdrental.utils.DBTestUtils;

class _08_TestCreateAndStoreEntityRental {

	private DBTestUtils dbUtils;
	private DvdRentalExerciseJpaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		dbUtils = new DBTestUtils();
		repository = Factory.createDvdRentalExerciseRepository();
		assertNotNull(repository);
	}

	@Test
	void testValidParameters() {
		Date rentalDate = getTodayDate();
		Rental expected = new Rental() {
			public Date getReturnDate() {
				return null;
			}
			public Date getRentalDate() {
				return rentalDate;
			}
			public String getFilmTitle() {
				return "Bonnie Holocaust";
			}
			public String getCustomerEmail() {
				return "sergio.stanfield@sakilacustomer.org";
			}
		};
		
		try {
			Rental actual = repository.createAndStoreRental("Bonnie Holocaust", "28 MySQL Boulevard", "sergio.stanfield@sakilacustomer.org", "Mike");
			assertEquals(expected, actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(rentalDate);;
		}
	}
	
	@Test
	void testNullTitle() {
		try {
			Rental actual = repository.createAndStoreRental(null, "28 MySQL Boulevard", "sergio.stanfield@sakilacustomer.org", "Mike");
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	@Test
	void testNonExistingTitle() {
		try {
			Rental actual = repository.createAndStoreRental("xxx", "28 MySQL Boulevard", "sergio.stanfield@sakilacustomer.org", "Mike");
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	@Test
	void testNonExistingStore() {
		try {
			Rental actual = repository.createAndStoreRental("Bonnie Holocaust", "12345 azerty", "sergio.stanfield@sakilacustomer.org", "Mike");
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	@Test
	void testNullStore() {
		try {
			Rental actual = repository.createAndStoreRental("Bonnie Holocaust", null, "sergio.stanfield@sakilacustomer.org", "Mike");
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	@Test
	void testNonExistingEmail() {
		try {
			Rental actual = repository.createAndStoreRental("Bonnie Holocaust", "28 MySQL Boulevard", "zzzz@xxxxxx", "Mike");
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	@Test
	void testNullEmail() {
		try {
			Rental actual = repository.createAndStoreRental("Bonnie Holocaust", "28 MySQL Boulevard", null, "Mike");
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	@Test
	void testNullStaffUsername() {
		try {
			Rental actual = repository.createAndStoreRental("Bonnie Holocaust", "28 MySQL Boulevard", "sergio.stanfield@sakilacustomer.org", null);
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	@Test
	void testNnonExistingStaffUsername() {
		try {
			Rental actual = repository.createAndStoreRental("Bonnie Holocaust", "28 MySQL Boulevard", "sergio.stanfield@sakilacustomer.org", "Betty");
			assertNull(actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(getTodayDate());
		}
	}
	
	private Date getTodayDate() {
		Calendar c = Calendar.getInstance();
		return dbUtils.createDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
	}
}
