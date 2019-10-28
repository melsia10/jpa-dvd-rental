package be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Film")
@Table(name="film")
public class FilmEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id") 
	private Integer id;
	
	private String title;
	
	public String getTitle() {
		return title;
	}
}
