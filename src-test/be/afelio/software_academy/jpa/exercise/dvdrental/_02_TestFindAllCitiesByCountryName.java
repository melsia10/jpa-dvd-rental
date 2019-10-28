package be.afelio.software_academy.jpa.exercise.dvdrental;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.*;

class _02_TestFindAllCitiesByCountryName {

	private DvdRentalExerciseJpaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		repository = Factory.createDvdRentalExerciseRepository();
		assertNotNull(repository);
	}

	@Test
	void testExistingCountry() {
		List<? extends City> expected = createTestCities();
		
		List<? extends City> actual = repository.findAllCitiesByCountryName("France");
		System.out.println(actual);
		Collections.sort(actual, Comparator.comparing(c -> c.getName()));
		
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
	
	private List<? extends City> createTestCities() {
		List<City> list = new ArrayList<>();
		final Country france = new Country() {
			public String getName() {
				return "France";
			}
		};
		list.add(new City() {
			public String getName() {
				return "Brest";
			}
			public Country getCountry() {
				return france;
			}
		});
		list.add(new City() {
			public String getName() {
				return "Le Mans";
			}
			public Country getCountry() {
				return france;
			}
		});
		list.add(new City() {
			public String getName() {
				return "Toulon";
			}
			public Country getCountry() {
				return france;
			}
		});
		list.add(new City() {
			public String getName() {
				return "Toulouse";
			}
			public Country getCountry() {
				return france;
			}
		});
		return list;
	}
}
