package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.*;

@Entity(name="Customer")
@Table(name="customer")
@NamedQueries({
        @NamedQuery(name="findOneCustomerByFirstnameAndName",
                query="select c from Customer c "
                        + "where c.firstname = ?1 and c.lastname = ?2"),
        @NamedQuery(name = "findOneCustomerById",
                query = "select c from Customer c " +
                        "where c.id = ?1"),
        @NamedQuery(name="findOneCustomerByEmail",
                query="select c from Customer c "
                        + "where c.email = ?1")

})
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name ="email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
