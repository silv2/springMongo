import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sk.mongodb.example.dao.CustomerDAO;
import sk.mongodb.example.entity.Customer;
import sk.mongodb.example.exceptions.CustomerEmptyResultException;
import sk.mongodb.example.service.impl.CustomerServiceImpl;

import java.util.Date;

/**
 * Created by Silvia on 8. 10. 2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:**/mongodb-example-spring-config.xml" })
public class TestMongoDB {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:**/mdwh-client-spring-config.xml");

    @Autowired(required=true)
    public CustomerDAO repository;

    @Test
    public void saveCustomer(){
        CustomerServiceImpl.getInstance().saveCustomer("ID1","Silvia","Macejkova",new Date(1995, 11, 4));
    }

    @Test
    public void findById() throws CustomerEmptyResultException {
        System.out.println(CustomerServiceImpl.getInstance().findCustomerById("ID1").toString());
    }

    @Test
    public void findAllCustomers() throws CustomerEmptyResultException {
        Iterable<Customer> allCustomers = CustomerServiceImpl.getInstance().getAllCustomers();
        for(Customer o : allCustomers) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void deleteCustomer(){
        CustomerServiceImpl.getInstance().deleteCustomer("543554de9a5820492a1b7785");
    }

    @Test
    public void findCustomersByDate() throws CustomerEmptyResultException {
        Iterable<Customer> allCustomers = CustomerServiceImpl.getInstance().findCustomersByDate(new Date(1990, 1, 1), new Date(2001, 1, 1));
        for(Customer o : allCustomers) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void findAllCustomersPageable() throws CustomerEmptyResultException {
        Page<Customer> listPageable=CustomerServiceImpl.getInstance().getAllCustomersPageable(new PageRequest(0, 10));
       for(Customer o : listPageable) {
            System.out.println(o.toString());
        }


        System.out.println(listPageable.getNumberOfElements());

    }

}
