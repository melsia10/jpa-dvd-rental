package be.afelio.software_academy.jpa.example.dvdrental.entities;

import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Country;

import javax.persistence.*;
import java.util.List;

@Entity(name="Country")
@Table(name="country")
@NamedQueries({
        @NamedQuery(name="findOneCountryByName",
                query="select c from Country c "
                        + "where c.name = ?1")
})
public class CountryEntity extends Country {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="country_id")
    private Integer id;
    @Column(name="country")
    private String name;
    @OneToMany(mappedBy = "country")
    private List<CityEntity> cities;


    @Override
    public String getName() {
        return name;
    }

}
