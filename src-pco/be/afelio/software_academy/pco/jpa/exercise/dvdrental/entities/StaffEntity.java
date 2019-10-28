package be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Staff")
@Table(name="staff")
@NamedQueries({
	@NamedQuery(name="findOneStaffByUsername", query="select s from Staff s where s.username = ?1")
})
public class StaffEntity {

	@Id
	@Column(name="staff_id") 
	private Integer id;
	
	@SuppressWarnings("unused")
	private String username;
}
