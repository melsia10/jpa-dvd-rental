package be.afelio.software_academy.jpa.example.dvdrental.entities;

import be.afelio.software_academy.jpa.exercise.dvdrental.DvdRentalExerciseJpaRepository;
import be.afelio.software_academy.jpa.exercise.dvdrental.Factory;
import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Rental;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="Rental")
@Table(name="rental")
@NamedQueries({
        @NamedQuery(name="findAllRentalsByFilmTitle",
                query="select r from Rental r "
                        + "where r.inventory.film.title = ?1"),
        @NamedQuery(name = "findOneRentalById",
                query = "select r from Rental r " +
                        "where r.id = ?1")
})
public class RentalEntity extends Rental {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @Column(name = "rental_date")
    @Temporal(TemporalType.DATE)
    private Date rentalDate;
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @ManyToOne
    @JoinColumn (name = "staff_id")
    private StaffEntity staff;


    @Override
    public String getFilmTitle() {
        return inventory.getFilm().getTitle();
    }

    @Override
    public String getCustomerEmail() {
        return customer.getEmail() ;
    }

    @Override
    public Date getRentalDate() {
        return rentalDate;
    }

    @Override
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }
}
