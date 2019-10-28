package be.afelio.software_academy.jpa.example.dvdrental.entities;

import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Address;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.City;

import javax.persistence.*;


@Entity(name="Address")
@Table(name="address")
@NamedQueries({
        @NamedQuery(
                name="findAllStoreAddressesByCountryName",
                query="select a from Address a where a.city.country.name = ?1")
})

public class AddressEntity extends Address {


    @Id
    @Column(name = "address_id")
    private Integer id;
    @Column(name = "address")
    private String value;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public City getCity() {
        return city;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
