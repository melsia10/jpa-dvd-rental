package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Inventory")
@Table(name="inventory")
@NamedQueries({
        @NamedQuery(name="findOneInventoryById",
                query="select i from Inventory i "
                        + "where i.id = ?1"),
        @NamedQuery(name = "findOneInventoryByFilmId",
                query = "select i from Inventory i " +
                        "where i.film.id = ?1")
})

public class InventoryEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="inventory_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmEntity film;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FilmEntity getFilm() {
        return film;
    }

    public void setFilm(FilmEntity film) {
        this.film = film;
    }
}

