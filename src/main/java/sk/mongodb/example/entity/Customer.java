package sk.mongodb.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Silvia on 8. 10. 2014.
 */

@Document(collection = "customer")
@CompoundIndexes({
        @CompoundIndex(name = "wholeName", def = "{'firstName': 1, 'lastName': 1}")
})
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Date registration;
    @DBRef
    private Shop shop;

    public Customer(String id, String firstName, String lastName, Date registration, Shop shop) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registration = registration;
        this.shop = shop;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registration=" + registration +
                ", shop=" + shop.getName() + " address = " + shop.getAddress() +
                '}';
    }


}
