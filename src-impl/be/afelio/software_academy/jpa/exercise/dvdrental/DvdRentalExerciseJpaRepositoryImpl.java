package be.afelio.software_academy.jpa.exercise.dvdrental;

import be.afelio.software_academy.jpa.example.dvdrental.entities.*;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Address;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.City;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Rental;
import be.afelio.software_academy.jpa.exercise.dvdrental.exceptions.DuplicatedCityException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DvdRentalExerciseJpaRepositoryImpl implements DvdRentalExerciseJpaRepository {

    private EntityManager em;

    public DvdRentalExerciseJpaRepositoryImpl(EntityManager em) {
        super();
        this.em = em;
    }

    @Override
    public CountryEntity findOneCountryByName(String name) {
        CountryEntity country = null;
        if (name != null && !name.isBlank()) {
            try {
                TypedQuery<CountryEntity> query = em.createNamedQuery("findOneCountryByName", CountryEntity.class);
                query.setParameter(1, name);
                country = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return country;
    }

    @Override
    public List<? extends City> findAllCitiesByCountryName(String name) {
        TypedQuery<CityEntity> query
                = em.createNamedQuery("findAllCitiesByCountryName", CityEntity.class);
        query.setParameter(1, name);
        List<CityEntity> list = query.getResultList();
        return list;
    }

    @Override
    public List<? extends Address> findAllStoreAddressesByCountryName(String name) {
        List<AddressEntity> addressList = null;
        if (name != null && !name.isBlank()) {
            try {
                TypedQuery<AddressEntity> query = em.createNamedQuery("findAllStoreAddressesByCountryName", AddressEntity.class);
                query.setParameter(1, name);
                addressList = query.getResultList();
            } catch (NoResultException ignored) {
            }
        }
        return addressList;
    }

    @Override
    public City createCity(String cityName, String countryName) {
        CityEntity city = null;
        if (cityName != null && !cityName.isBlank()
                && countryName != null && !countryName.isBlank()) {
            if (findOneCityByName(cityName) != null) {
                throw new DuplicatedCityException();
            }
            city = new CityEntity();
            city.setName(cityName);
            CountryEntity country = findOneCountryByName(countryName);
            if (country != null) {
                city.setCountry(country);
            } else {
                country = createCountry(countryName);
            }
            if (em.isJoinedToTransaction()) {
                em.persist(city);
            } else {
                em.getTransaction().begin();
                em.persist(city);
                em.getTransaction().commit();
                city = findOneCityByName(cityName);
            }
        }
        return city;
    }

    private CountryEntity createCountry(String countryName) {
        CountryEntity country = null;
        if (countryName != null && !countryName.isBlank()) {
            if (em.isJoinedToTransaction()) {
                em.persist(country);
            } else {
                em.getTransaction().begin();
                em.persist(country);
                em.getTransaction().commit();
                country = findOneCountryByName(countryName);
            }
        }
        return country;
    }

    public CityEntity findOneCityByName(String name) {
        CityEntity city = null;
        if (name != null && !name.isBlank()) {
            try {
                TypedQuery<CityEntity> query = em.createNamedQuery("findOneCityByName", CityEntity.class);
                query.setParameter(1, name);
                city = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return city;
    }

    @Override
    public boolean deleteCity(String cityName, String countryName) {
        boolean deleted = false;
        if (cityName != null && !cityName.isBlank()
                && countryName != null && !countryName.isBlank()) {
            CountryEntity country = findOneCountryByName(countryName);
            CityEntity city = findOneCityByName(cityName);
            if (city != null) {
                city.setCountry(country);
                if (em.isJoinedToTransaction()) {
                    em.remove(city);
                } else {
                    em.getTransaction().begin();
                    em.remove(city);
                    em.getTransaction().commit();
                }
                deleted = true;
            }
        }
        return deleted;
    }

    @Override
    public List<? extends Rental> findAllRentalsByFilmTitle(String title) {
        List<RentalEntity> rentalList = null;
        if (title != null && !title.isBlank()) {
            try {
                TypedQuery<RentalEntity> query = em.createNamedQuery("findAllRentalsByFilmTitle", RentalEntity.class);
                query.setParameter(1, title);
                rentalList = query.getResultList();
            } catch (NoResultException ignored) {
            }
        }
        return rentalList;
    }

    @Override
    public boolean updateRentalReturnDate(int rentalId, Date returnDate) {
        RentalEntity rental = null;
        boolean updated = false;
        if (rentalId != 0 && returnDate != null) {
            try {
                TypedQuery<RentalEntity> query = em.createNamedQuery("findOneRentalById", RentalEntity.class);
                query.setParameter(1, rentalId);
                rental = query.getSingleResult();
                if (rental != null) {
                    if (rental.getRentalDate().compareTo(returnDate) < 0) {
                        rental.setReturnDate(returnDate);
                        if (em.isJoinedToTransaction()) {
                            em.persist(rental);
                        } else {
                            em.getTransaction().begin();
                            em.persist(rental);
                            em.getTransaction().commit();
                        }
                        updated = true;
                    }
                }
            } catch (NoResultException ignored) {
            }
        }

        return updated;
    }

    @Override
    public RentalEntity createAndStoreRental(String filmTitle, String storeAddress, String customerEmail, String staffUsername) {
        RentalEntity rental = null;
        if (filmTitle != null && !filmTitle.isBlank()
                && storeAddress != null && !storeAddress.isBlank()
                && customerEmail != null && !customerEmail.isBlank()
                && staffUsername != null && !staffUsername.isBlank()) {
            FilmEntity film = findOneFilmByTitle(filmTitle);
            if (film != null) {
                StoreEntity store = findOneStoreByAddress(storeAddress);
                CustomerEntity customer = findOneCustomerByEmail(customerEmail);
                StaffEntity staff = findStaffByUsername(staffUsername);
                InventoryEntity inventory = findOneInventoryByFilmId(film.getId());
                inventory.setFilm(film);
                if(store != null && customer != null && staff != null ){
                    rental = new RentalEntity();
                    rental.setCustomer(customer);
                    rental.setInventory(inventory);
                        rental.setStaff(staff);
                        rental.setRentalDate(java.sql.Date.valueOf(LocalDate.now()));
                        if (em.isJoinedToTransaction()) {
                            em.persist(rental);
                        } else {
                            em.getTransaction().begin();
                            em.persist(rental);
                            em.getTransaction().commit();
                            rental = findOneRentalById(rental.getId());
                        }
                    }
            }
        }
        return rental;
    }

    private StaffEntity findStaffByUsername(String staffUsername) {
        StaffEntity staff = null;
        if (staffUsername != null && !staffUsername.isBlank()) {
            try {
                TypedQuery<StaffEntity> query = em.createNamedQuery("findStaffByUsername", StaffEntity.class);
                query.setParameter(1, staffUsername);
                staff = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return staff;
    }

    private InventoryEntity findOneInventoryByFilmId(int filmId) {
        InventoryEntity inventory = null;
        if (filmId != 0) {
            try {
                TypedQuery<InventoryEntity> query = em.createNamedQuery("findOneInventoryByFilmId", InventoryEntity.class);
                query.setParameter(1, filmId);
                query.setMaxResults(1);
                inventory = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return inventory;
    }

    private CustomerEntity findOneCustomerByEmail(String customerEmail) {
        CustomerEntity customer = null;
        if (customerEmail != null && !customerEmail.isBlank()) {
            try {
                TypedQuery<CustomerEntity> query = em.createNamedQuery("findOneCustomerByEmail", CustomerEntity.class);
                query.setParameter(1, customerEmail);
                customer = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return customer;
    }

    private StoreEntity findOneStoreByAddress(String storeAddress) {
        StoreEntity store = null;
        if (storeAddress != null && !storeAddress.isBlank()) {
            try {
                TypedQuery<StoreEntity> query = em.createNamedQuery("findOneStoreByAddress", StoreEntity.class);
                query.setParameter(1, storeAddress);
                store = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return store;
    }

    private FilmEntity findOneFilmByTitle(String filmTitle) {
        FilmEntity film = null;
        if (filmTitle != null && !filmTitle.isBlank()) {
            try {
                TypedQuery<FilmEntity> query = em.createNamedQuery("findOneFilmByTitle", FilmEntity.class);
                query.setParameter(1, filmTitle);
                film = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return film;
    }

    private RentalEntity findOneRentalById(Integer id) {
        RentalEntity rental = null;
        if (id != 0) {
            try {
                TypedQuery<RentalEntity> query = em.createNamedQuery("findOneRentalById", RentalEntity.class);
                query.setParameter(1, id);
                rental = query.getSingleResult();
            } catch (NoResultException ignored) {
            }
        }
        return rental;
    }
}
