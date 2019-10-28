package be.afelio.software_academy.pco.jpa.exercise.dvdrental;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Address;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.City;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Rental;
import be.afelio.software_academy.jpa.exercise.dvdrental.exceptions.DuplicatedCityException;
import be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities.AddressEntity;
import be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities.CityEntity;
import be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities.CountryEntity;
import be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities.CustomerEntity;
import be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities.InventoryEntity;
import be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities.RentalEntity;
import be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities.StaffEntity;

public class DvdRentalExercisePcoJpaRepository implements DvdRentalExerciseJpaRepository{

	private EntityManager em;
	
	public DvdRentalExercisePcoJpaRepository(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public CountryEntity findOneCountryByName(String name) {
		return queryForObject("findOneCountryByName", CountryEntity.class, name);
	}

	@Override
	public List<? extends City> findAllCitiesByCountryName(String name) {
		if (name != null && !name.isBlank()) {
			return queryForList("findAllCitiesByCountryName", CityEntity.class, name);
		} 
		return Collections.emptyList();
	}

	@Override
	public List<? extends Address> findAllStoreAddressesByCountryName(String name) {
		if (name != null && !name.isBlank()) {
			return queryForList("findAllStoreAddressesByCountryName", AddressEntity.class, name);
		}
		return Collections.emptyList();
	}

	@Override
	public CityEntity createCity(String cityName, String countryName) {
		CityEntity city = null;
		if (cityName != null && !cityName.isBlank() 
				&& countryName != null && !countryName.isBlank()) {
			if (findOneCityByNameAndCountryName(cityName, countryName) != null) {
				throw new DuplicatedCityException();
			}
			CountryEntity country = findOneCountryByName(countryName);
			if (country == null) {
				country = new CountryEntity();
				country.setName(countryName);
				saveEntity(country);
			}
			city = new CityEntity();
			city.setCountry(country);
			city.setName(cityName);
			saveEntity(city);
		}
		return city;
	}

	protected CityEntity findOneCityByNameAndCountryName(String cityName, String countryName) {
		return queryForObject("findOneCityByNameAndCountryName", CityEntity.class, cityName, countryName);
	}
	
	@Override
	public boolean deleteCity(String cityName, String countryName) {
		boolean deleted = false;
		CityEntity city = findOneCityByNameAndCountryName(cityName, countryName);
		if (city != null) {
			deleteEntity(city);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<? extends Rental> findAllRentalsByFilmTitle(String title) {
		return queryForList("findAllRentalsByFilmTitle", RentalEntity.class, title);
	}

	@Override
	public boolean updateRentalReturnDate(int rentalId, Date returnDate) {
		boolean updated = false;
		if (returnDate != null) {
			RentalEntity rental = findOneRentalById(rentalId);
			if (rental != null && rental.getRentalDate().before(returnDate)) {
				rental.setReturnDate(returnDate);
				saveEntity(rental);
				updated = true;
			}
		}
		return updated;
	}

	protected RentalEntity findOneRentalById(int id) {
		if (id > 0) {
			return em.find(RentalEntity.class, id);
		}
		return null;
	}
	
	@Override
	public RentalEntity createAndStoreRental(String filmTitle, String storeAddress, String customerEmail, String staffUsername) {
		RentalEntity rental = null;
		InventoryEntity inventory = findOneInventoryByFilmTitleAndStoreAddress(filmTitle, storeAddress);
		if (inventory != null) {
			CustomerEntity customer = findOneCustomerByEmail(customerEmail);
			if (customer != null) {
				StaffEntity employee = findOneStaffByUsername(staffUsername);
				if (employee != null) {
					rental = new RentalEntity();
					rental.setCustomer(customer);
					rental.setInventory(inventory);
					rental.setEmployee(employee);
					rental.setRentalDate(java.sql.Date.valueOf(LocalDate.now()));
					saveEntity(rental);
				}
			}
		}
		return rental;
	}
	
	protected StaffEntity findOneStaffByUsername(String username) {
		if (username != null && !username.isBlank()) {
			return queryForObject("findOneStaffByUsername", StaffEntity.class, username);
		}
		return null;
	}
	
	protected InventoryEntity findOneInventoryByFilmTitleAndStoreAddress(String filmTitle, String storeAddress) {
		if (filmTitle != null && !filmTitle.isBlank()
				&& storeAddress != null && !storeAddress.isBlank()) {
			return queryForObject("findOneInventoryByFilmTitleAndStoreAddress", InventoryEntity.class, filmTitle, storeAddress);
		}
		return null;
	}
	
	protected CustomerEntity findOneCustomerByEmail(String email) {
		if (email != null && !email.isBlank()) {
			return queryForObject("findOneCustomerByEmail", CustomerEntity.class, email);
		}
		return null;
	}
	
	protected void saveEntity(Object entity) {
		if (em.isJoinedToTransaction()) {
			em.persist(entity);
		} else {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		}
	}
	
	protected void deleteEntity(Object entity) {
		if (em.isJoinedToTransaction()) {
			em.remove(entity);
		} else {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		}
	}
	
	protected <T> T queryForObject(String namedQuery, Class<T> resultClass, Object... parameters) {
		TypedQuery<T> query = em.createNamedQuery(namedQuery, resultClass);
		query.setMaxResults(1);
		int index = 1;
		for (Object param : parameters) {
			query.setParameter(index, param);
			index++;
		}
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	protected <T> List<T> queryForList(String namedQuery, Class<T> resultClass, Object... parameters) {
		TypedQuery<T> query = em.createNamedQuery(namedQuery, resultClass);
		int index = 1;
		for (Object param : parameters) {
			query.setParameter(index, param);
			index++;
		}
		return query.getResultList();
	}
}
