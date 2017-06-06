package info.owerczuk.npcProject.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**



 **/
@Entity
@Table(name = "customer") // przy użyciu nazwy "User" Hibernate wyrzuca błąd składni ...
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name ="username")
    private String username;

    @Column(name = "pass")
    private String password;


    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Contacts> contacts = new ArrayList<>();

    protected User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }
}
