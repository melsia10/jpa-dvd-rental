package be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities;

import java.util.Date;

import javax.persistence.*;

import be.afelio.software_academy.jpa.exercise.dvdrental.beans.Rental;

@Entity(name="Rental")
@Table(name="rental")
@NamedQueries({
	@NamedQuery(name="findAllRentalsByFilmTitle", query="select r from Rental r where r.inventory.film.title = ?1 order by r.rentalDate")
})
public class RentalEntity extends Rental {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id") 
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="rental_date")
	private Date rentalDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="return_date")
	private Date returnDate;
	
	@ManyToOne
	@JoinColumn(name="inventory_id")
	private InventoryEntity inventory;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private CustomerEntity customer;
	
	@ManyToOne
	@JoinColumn(name="staff_id")
	private StaffEntity employee;

	@Override
	public String getFilmTitle() {
		if (inventory != null && inventory.getFilm() != null) {
			return inventory.getFilm().getTitle();
		}
		return null;
	}

	@Override
	public String getCustomerEmail() {
		if (customer != null) {
			return customer.getEmail();
		}
		return null;
	}

	@Override
	public Date getRentalDate() {
		return rentalDate;
	}

	@Override
	public Date getReturnDate() {
		return returnDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	
	public void setInventory(InventoryEntity inventory) {
		this.inventory = inventory;
	}
	
	public void setEmployee(StaffEntity employee) {
		this.employee = employee;
	}
}
