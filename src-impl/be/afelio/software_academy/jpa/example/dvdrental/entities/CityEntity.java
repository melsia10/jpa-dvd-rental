package be.afelio.software_academy.jpa.example.dvdrental.entities;

import be.afelio.software_academy.jpa.exercise.dvdrental.beans.City;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Country;

import javax.persistence.*;

@Entity(name="City")
@Table(name="city")
@NamedQueries({
        @NamedQuery(name="findAllCitiesByCountryName",
                query="select c from City c "
                        + "where c.country.name = ?1"),
        @NamedQuery(
                name="findOneCityByName",
                query="select c from City c where c.name = ?1")
})
public class CityEntity extends City {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="city_id")
    private Integer id;
    @Column(name="city")
    private String name;
    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity country;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}
