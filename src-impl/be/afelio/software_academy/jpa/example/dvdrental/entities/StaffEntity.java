package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name="Staff")
@Table(name="staff")
@NamedQueries({
        @NamedQuery(name="findOneStaffById",
                query="select s from Staff s "
                        + "where s.id = ?1"),
        @NamedQuery(name="findStaffByUsername",
                query="select s from Staff s "
                        + "where s.firstname = ?1"),

})
public class StaffEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;
    @OneToMany(mappedBy="staff")
    private List<StoreEntity> stores;



    @Column(name = "first_name")
    private String firstname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<StoreEntity> getStores() {
        return stores;
    }

    public void setStores(List<StoreEntity> stores) {
        this.stores = stores;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
