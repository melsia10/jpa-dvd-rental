package be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities;

import javax.persistence.*;

import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Address;

@Entity(name="Address")
@Table(name="address")
@NamedQueries({
	@NamedQuery(name="findAllStoreAddressesByCountryName", query="select a from Address a where a.city.country.name = ?1")
})
public class AddressEntity extends Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id") 
	private Integer id;
	
	@Column(name="address")
	private String value;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private CityEntity city;
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public CityEntity getCity() {
		return city;
	}

}
