package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Store")
@Table(name="store")
@NamedQueries({
        @NamedQuery(name="findOneStoreById",
                query="select s from Store s "
                        + "where s.id = ?1"),
        @NamedQuery(name="findOneStoreByAddress",
                query="select s from Store s "
                        + "where s.address.value = ?1")
})
public class StoreEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "manager_staff_id")
    private StaffEntity staff;
    @ManyToOne
    @JoinColumn(name="address_id")
    private AddressEntity address;

    public Integer getId() {
        return id;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;

    }
}
