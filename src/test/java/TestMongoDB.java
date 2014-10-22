import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.mongodb.example.entity.Customer;
import sk.mongodb.example.entity.Shop;
import sk.mongodb.example.exceptions.CustomerEmptyResultException;
import sk.mongodb.example.exceptions.ShopEmptyResultException;
import sk.mongodb.example.service.impl.CustomerServiceImpl;
import sk.mongodb.example.service.impl.ShopServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by Silvia on 8. 10. 2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/mongodb-example-spring-config.xml" })
public class TestMongoDB {


    @Autowired(required=true)
    public CustomerServiceImpl customService;
    @Autowired(required=true)
    public ShopServiceImpl shopService;


    @Test
    public void saveShop(){
        shopService.saveShop("IDS2", "SHOP2", "ADDRESS2");
    }

    @Test
    public void saveCustomer() throws ShopEmptyResultException {
        customService.saveCustomer("ID1", "Zuzka", "Mrkvickova", new Date(1990, 11, 4), shopService.findShopById("IDS"));
    }

    @Test
    public void findCustomerById() throws CustomerEmptyResultException {
        System.out.println(customService.findCustomerById("ID1").toString());
    }

    @Test
    public void findAllCustomers() throws CustomerEmptyResultException {
        Iterable<Customer> allCustomers = customService.getAllCustomers();
        for(Customer o : allCustomers) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void deleteCustomer(){
        customService.deleteCustomer("ID1");
    }

    @Test
    public void findCustomersByDate() throws CustomerEmptyResultException {
        Iterable<Customer> allCustomers = customService.findCustomersByDate(new Date(1989, 1, 1), new Date(2001, 1, 1));
        for(Customer o : allCustomers) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void findAllCustomersPageable() throws CustomerEmptyResultException {
        Page<Customer> listPageable = customService.getAllCustomersPageable(new PageRequest(0, 10));
       for(Customer o : listPageable) {
            System.out.println(o.toString());
        }


        System.out.println(listPageable.getNumberOfElements());

    }

    @Test
    public void getCustomersFromShop() throws ShopEmptyResultException, CustomerEmptyResultException {
        List<Customer> listCustomer =  customService.findCustomersFromShop(shopService.findShopById("IDS"));
        for(Customer o : listCustomer) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void deleteShop(){
        shopService.deleteShop("IDS");
    }

    @Test
    public void findAllShops() throws ShopEmptyResultException {
        Iterable<Shop> allShops = shopService.getAllShops();
        for(Shop o : allShops) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void findShopById() throws ShopEmptyResultException {
        System.out.println(shopService.findShopById("IDS").toString());
    }


}
