package be.afelio.software_academy.jpa.exercise.dvdrental;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.*;

class _01_TestFindOneCountryByName {

	private DvdRentalExerciseJpaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		repository = Factory.createDvdRentalExerciseRepository();
		assertNotNull(repository);
	}

	@Test
	void testExistingCountry() {
		Country expected = new Country() {
			public String getName() {
				return "France";
			}
		};
		
		Country actual = repository.findOneCountryByName(expected.getName());
		
		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingCountry() {
		String name = "Belgium";
		
		Country country = repository.findOneCountryByName(name);
		
		assertNull(country);
	}
	
	@Test
	void testNullCountry() {
		String name = null;
		
		Country country = repository.findOneCountryByName(name);
		
		assertNull(country);
	}
}
