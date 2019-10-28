package be.afelio.software_academy.jpa.exercise.dvdrental;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.utils.DBTestUtils;

class _05_TestDeleteCity {

	private DBTestUtils dbUtils;
	private DvdRentalExerciseJpaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		dbUtils = new DBTestUtils();
		repository = Factory.createDvdRentalExerciseRepository();
		assertNotNull(repository);
	}

	@Test
	void testExistingCity() {
		dbUtils.insertCity("Paris", 34);
		try {
			assertTrue(repository.deleteCity("Paris", "France"));
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteCity("Paris");
		}
	}
	
	@Test
	void testNonExistingCity() {
		String name = String.valueOf(System.currentTimeMillis());
		assertFalse(repository.deleteCity(name, "France"));
	}
	
	@Test
	void testExistingCityInDifferentCountry() {
		String name = String.valueOf(System.currentTimeMillis());
		dbUtils.insertCity(name, 34);
		try {
			boolean deleted = repository.deleteCity(name, "Finland");
			assertFalse(deleted);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteCity(name);
		}
	}
}
