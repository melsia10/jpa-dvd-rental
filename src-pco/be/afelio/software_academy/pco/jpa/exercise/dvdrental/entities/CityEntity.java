package be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities;

import javax.persistence.*;

import be.afelio.software_academy.jpa.exercise.dvdrental.beans.City;

@Entity(name="City")
@Table(name="city")
@NamedQueries({
	@NamedQuery(name="findAllCitiesByCountryName", query="select c from City c where c.country.name = ?1"),
	@NamedQuery(name="findOneCityByNameAndCountryName", query="select c from City c where c.name = ?1 and c.country.name = ?2")
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
	public CountryEntity getCountry() {
		return country;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}
}
