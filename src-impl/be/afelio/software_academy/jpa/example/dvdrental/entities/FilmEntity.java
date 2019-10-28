package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Film")
@Table(name="film")
@NamedQueries({
        @NamedQuery(name="findOneFilmByTitle",
                query="select f from Film f "
                        + "where f.title = ?1")
})
public class FilmEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="film_id")
    private Integer id;
    @Column(name = "title")
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
