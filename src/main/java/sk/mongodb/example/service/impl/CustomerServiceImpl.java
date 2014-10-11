package sk.mongodb.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import sk.mongodb.example.dao.CustomerDAO;
import sk.mongodb.example.entity.Customer;
import sk.mongodb.example.entity.Shop;
import sk.mongodb.example.exceptions.CustomerEmptyResultException;
import sk.mongodb.example.service.CustomerService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by Silvia on 8. 10. 2014.
 */
@Service
public class CustomerServiceImpl implements CustomerService{
   /* private static CustomerServiceImpl instance;
    private  ApplicationContext appContext;*/
    private MongoOperations mongoOperation;
    @Resource
    private MongoTemplate mongoTemplate;


    @Autowired(required=true)
    CustomerDAO repository;

    @Override
    public void saveCustomer(String id, String firstName, String lastName, Date date, Shop shop){

        Customer customer = new Customer(id, firstName, lastName, date, shop);
        repository.save(customer);
    }

    @Override
    public void deleteCustomer(String id){
        repository.delete(id);
    }

    @Override
    public Customer findCustomerById(String id) throws CustomerEmptyResultException {
        Customer customer = repository.findOne(id);
        if(customer == null){
            throw new CustomerEmptyResultException("Method findCustomerById(String id) return null");
        }
        return customer;

    }

    @Override
    public Iterable<Customer> getAllCustomers() throws CustomerEmptyResultException {
        Iterable<Customer> allCustomers = repository.findAll();
        if(allCustomers == null){
            throw new CustomerEmptyResultException("Method getAllCustomers() return null");
        }
        return allCustomers;
    }

    @Override
    public List<Customer> findCustomersByDate(Date from, Date to) throws CustomerEmptyResultException {
        Query query = new Query();
        query.addCriteria(where("registration").exists(true).andOperator(
                where("registration").gt(from),
                where("registration").lt(to)
        ));
       List<Customer> customers = mongoOperation.find(query,Customer.class,"customer");

        if(customers == null){
            throw new CustomerEmptyResultException("Method findCustomersByDate(Date from, Date to) return null");
        }

        return customers;
    }

    @Override
    public Page getAllCustomersPageable(PageRequest request) throws CustomerEmptyResultException {
        Page<Customer> allCustomersPageable = repository.findAll(request);
        if (allCustomersPageable == null) {
            throw new CustomerEmptyResultException("Method getAllCustomersPageable(PageRequest request) return null");
        }
        return allCustomersPageable;
    }

    public List<Customer> findCustomersFromShop(Shop shop) {
        Query query = new Query(where("shop.$id").is(shop.getId()));
        List<Customer> listCustomers = mongoTemplate.find(query, Customer.class);
        return listCustomers;
    }

}
