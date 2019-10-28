package be.afelio.software_academy.jpa.exercise.dvdrental;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.*;
import be.afelio.software_academy.jpa.exercise.dvdrental.exceptions.DuplicatedCityException;
import be.afelio.software_academy.jpa.exercise.dvdrental.utils.DBTestUtils;

class _04_TestCreateCity {

	private DBTestUtils dbUtils;
	private DvdRentalExerciseJpaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		dbUtils = new DBTestUtils();
		repository = Factory.createDvdRentalExerciseRepository();
		assertNotNull(repository);
	}

	@Test
	void testNonExistingCityInExistingCountry() {
		City expected = new City() {
			@Override
			public String getName() {
				return "Paris";
			}
			public Country getCountry() {
				return new Country() {
					public String getName() {
						return "France";
					}
				};
			}
		};
		
		try {
			City actual = repository.createCity("Paris", "France");
			assertEquals(expected, actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteCity("Paris");
		}
	}
	
	@Test
	void testNonExistingCityInNonExistingCountry() {
		City expected = new City() {
			@Override
			public String getName() {
				return "Liège";
			}
			public Country getCountry() {
				return new Country() {
					public String getName() {
						return "Belgium";
					}
				};
			}
		};
		
		try {
			City actual = repository.createCity("Liège", "Belgium");
			assertEquals(expected, actual);
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteCity("Liège");
			dbUtils.deleteCountry("Belgium");
		}
	}
	
	@Test
	void testNullCity() {
		City actual = repository.createCity(null, "France");
		assertNull(actual);
	}
	
	@Test
	void testBlankCity() {
		City actual = repository.createCity("", "France");
		assertNull(actual);
	}
	
	@Test
	void testNullCountry() {
		City actual = repository.createCity("Paris", null);
		assertNull(actual);
	}
	
	@Test
	void testBlankCountry() {
		City actual = repository.createCity("Paris", "");
		assertNull(actual);
	}
	
	@Test
	void testDuplicatedCity() {
		String name = String.valueOf(System.currentTimeMillis());
		dbUtils.insertCity(name, 34);
		try {
			assertThrows(DuplicatedCityException.class, () -> repository.createCity(name, "France"));
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			dbUtils.deleteCity(name);
		}
	}
}
