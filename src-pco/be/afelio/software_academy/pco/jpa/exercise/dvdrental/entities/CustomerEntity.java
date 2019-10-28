package be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Customer")
@Table(name="customer")
@NamedQueries({
	@NamedQuery(name="findOneCustomerByEmail", query="select c from Customer c where c.email = ?1")
})
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id") 
	private Integer id;
	
	private String email;
	
	public String getEmail() {
		return email;
	}
}
