package be.afelio.software_academy.jpa.exercise.dvdrental;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.utils.DBTestUtils;

class _07_TestUpdateRentalReturnDate {

	private DBTestUtils dbUtils;
	private DvdRentalExerciseJpaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		dbUtils = new DBTestUtils();
		repository = Factory.createDvdRentalExerciseRepository();
		assertNotNull(repository);
	}

	@Test
	void testExistingRentalValidDate() {
		int id = dbUtils.insertRental(1, 1, 1, dbUtils.createDate(2019, 9, 1));
		Date returnDate = dbUtils.createDate(2019, 9, 15);
		try {
			assertTrue(repository.updateRentalReturnDate(id, returnDate));
			assertEquals(returnDate, dbUtils.selectRentalReturnDate(id));
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(id);
		}
	}
	
	@Test
	void testNonExistingRental() {
		Date returnDate = dbUtils.createDate(2019, 9, 15);
		assertFalse(repository.updateRentalReturnDate(Integer.MAX_VALUE, returnDate));
	}
	
	@Test
	void testNullReturnDate() {
		int id = dbUtils.insertRental(1, 1, 1, dbUtils.createDate(2019, 9, 1));
		try {
			assertFalse(repository.updateRentalReturnDate(id, null));
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(id);
		}
	}
	
	@Test
	void testExistingRentalInvalidReturnDate() {
		int id = dbUtils.insertRental(1, 1, 1, dbUtils.createDate(2019, 9, 1));
		Date returnDate = dbUtils.createDate(2019, 8, 31);
		try {
			assertFalse(repository.updateRentalReturnDate(id, returnDate));
		} catch(Exception e) {
			throw e;
		} finally {
			dbUtils.deleteRental(id);
		}
	}
}
