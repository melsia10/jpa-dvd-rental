package be.afelio.software_academy.pco.jpa.exercise.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Inventory")
@Table(name="inventory")
@NamedQueries({
	@NamedQuery(name="findOneInventoryByFilmTitleAndStoreAddress", 
			query="select i from Inventory i where i.film.title = ?1 and i.store.address.value = ?2")
})
public class InventoryEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inventory_id") 
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="film_id")
	private FilmEntity film;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private StoreEntity store;
	
	public FilmEntity getFilm() {
		return film;
	}
	
	public StoreEntity getStore() {
		return store;
	}
}
