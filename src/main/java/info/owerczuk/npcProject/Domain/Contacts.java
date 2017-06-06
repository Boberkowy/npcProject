package info.owerczuk.npcProject.Domain;

import javax.persistence.*;
import java.util.Date;

/**

 **/
@Entity
@Table(name="contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name ="firstname")
    private String firstname;

    @Column(name ="lastname")
    private String lastname;

    @Column(name ="email", unique = true)
    private String email;

    @Column(name ="password")
    private String password;

    @Column(name ="category")
    private String category;

    @Column(name="subcategory")
    private String subcategory;

    @Column(name="phoneNumber")
    private String phoneNumber;


    @Column(name="dateOfBirth")
    private String dateOfBirth;

    protected Contacts(){}

    public Contacts(String firstname, String lastname, String email,String password, String category,  String subcategory, String phoneNumber, String dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.category = category;
        this.subcategory = subcategory;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
