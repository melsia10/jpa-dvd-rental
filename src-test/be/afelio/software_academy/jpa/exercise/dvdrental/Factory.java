package be.afelio.software_academy.jpa.exercise.dvdrental;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {

	public static DvdRentalExerciseJpaRepository createDvdRentalExerciseRepository() {
        DvdRentalExerciseJpaRepository repository = null;

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("dvdrental");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        repository = new DvdRentalExerciseJpaRepositoryImpl(entityManager);
        return repository;
	}
	
    public static String getDatabaseUrl() {
        return "jdbc:postgresql://localhost:5432/dvdrental";
    }

    public static String getDatabaseUser() {
        return "postgres";
    }

    public static String getDatabasePassword() {
        return "postgres";
    }
	
}
