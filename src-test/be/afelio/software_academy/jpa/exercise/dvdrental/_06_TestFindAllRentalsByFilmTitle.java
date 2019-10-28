package be.afelio.software_academy.jpa.exercise.dvdrental;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.*;

class _06_TestFindAllRentalsByFilmTitle {

	private DvdRentalExerciseJpaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		repository = Factory.createDvdRentalExerciseRepository();
		assertNotNull(repository);
	}

	@Test
	void testExistingFilmTitle() {
		List<? extends Rental> expected = createTestRentals();
		
		List<? extends Rental> actual = repository.findAllRentalsByFilmTitle("Mixed Doors");
		Collections.sort(actual, Comparator.comparing(r -> r.getRentalDate()));
		
		System.out.println(expected);
		System.out.println(actual);
		
		assertEquals(expected, actual);
	}

	private List<Rental> createTestRentals() {
		List<Rental> list = new ArrayList<>();
		list.add(new Rental() {
			public Date getRentalDate() {
				return createDate("2005-07-08");
			}
			public Date getReturnDate() {
				return createDate("2005-07-11");
			}
			public String getFilmTitle() {
				return "Mixed Doors";
			}
			public String getCustomerEmail() {
				return "pauline.henry@sakilacustomer.org";
			}
			
		});
		list.add(new Rental() {
			public Date getRentalDate() {
				return createDate("2005-07-27");
			}
			public Date getReturnDate() {
				return createDate("2005-08-04");
			}
			public String getFilmTitle() {
				return "Mixed Doors";
			}
			public String getCustomerEmail() {
				return "dana.hart@sakilacustomer.org";
			}
		});
		list.add(new Rental() {
			public Date getRentalDate() {
				return createDate("2005-07-28");
			}
			public Date getReturnDate() {
				return createDate("2005-08-01");
			}
			public String getFilmTitle() {
				return "Mixed Doors";
			}
			public String getCustomerEmail() {
				return "myrtle.fleming@sakilacustomer.org";
			}
		});
		list.add(new Rental() {
			public Date getRentalDate() {
				return createDate("2005-08-21");
			}
			public Date getReturnDate() {
				return createDate("2005-08-29");
			}
			public String getFilmTitle() {
				return "Mixed Doors";
			}
			public String getCustomerEmail() {
				return "edgar.rhoads@sakilacustomer.org";
			}
		});
		return list;
	}
	
	private Date createDate(String s) {
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
