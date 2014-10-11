package sk.mongodb.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Silvia on 8. 10. 2014.
 */

@Document(collection = "customer")
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Date registration;

    public Customer() {}

    public Customer( String id, String firstName, String lastName, Date registration) {
        this.registration = registration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registration=" + registration +
                '}';
    }


}
