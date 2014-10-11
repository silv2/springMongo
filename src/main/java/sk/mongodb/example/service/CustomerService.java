package sk.mongodb.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import sk.mongodb.example.entity.Customer;
import sk.mongodb.example.entity.Shop;
import sk.mongodb.example.exceptions.CustomerEmptyResultException;

import java.util.Date;
import java.util.List;

/**
 * Created by Silvia on 8. 10. 2014.
 */
public interface CustomerService {
    public void saveCustomer(String id, String firstName, String lastName, Date date, Shop shop);
    public void deleteCustomer(String id);
    public Customer findCustomerById(String id) throws CustomerEmptyResultException;
    public Iterable<Customer> getAllCustomers() throws CustomerEmptyResultException;
    public List<Customer> findCustomersByDate(Date from, Date to) throws CustomerEmptyResultException;
    public Page getAllCustomersPageable(PageRequest request) throws CustomerEmptyResultException;

}
