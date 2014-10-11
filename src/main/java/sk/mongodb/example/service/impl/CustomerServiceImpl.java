package sk.mongodb.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import sk.mongodb.example.dao.CustomerDAO;
import sk.mongodb.example.entity.Customer;
import sk.mongodb.example.exceptions.CustomerEmptyResultException;
import sk.mongodb.example.spring.ApplicationContextProvider;

import java.util.Date;
import java.util.List;

/**
 * Created by Silvia on 8. 10. 2014.
 */
@Service
public class CustomerServiceImpl {
    private static CustomerServiceImpl instance;
    private  ApplicationContext appContext;
    private MongoOperations mongoOperation;


    @Autowired(required=true)
    CustomerDAO repository;

    public void saveCustomer(String id, String firstName, String lastName, Date date){
        Customer customer = new Customer(id, firstName, lastName, date);
        repository.save(customer);
    }

    public void deleteCustomer(String id){
        repository.delete(id);
    }

    public Customer findCustomerById(String id) throws CustomerEmptyResultException {
        Customer customer = repository.findOne(id);
        if(customer == null){
            throw new CustomerEmptyResultException("Method findCustomerById(String id) return null");
        }
        return customer;

    }

    public Iterable<Customer> getAllCustomers() throws CustomerEmptyResultException {
        Iterable<Customer> allCustomers = repository.findAll();
        if(allCustomers == null){
            throw new CustomerEmptyResultException("Method getAllCustomers() return null");
        }
        return allCustomers;
    }

    public List<Customer> findCustomersByDate(Date from, Date to) throws CustomerEmptyResultException {
        Query query = new Query();
        query.addCriteria(Criteria.where("registration").exists(true).andOperator(
                Criteria.where("registration").gt(from),
                Criteria.where("registration").lt(to)
        ));


        List<Customer> customers = mongoOperation.find(query,Customer.class,"customer");

        if(customers == null){
            throw new CustomerEmptyResultException("Method findCustomersByDate(Date from, Date to) return null");
        }

        return customers;
    }
    public Page getAllCustomersPageable(PageRequest request) throws CustomerEmptyResultException {
        Page<Customer> allCustomersPageable = repository.findAll(request);
        if (allCustomersPageable == null) {
            throw new CustomerEmptyResultException("Method getAllCustomersPageable(PageRequest request) return null");
        }
        return allCustomersPageable;
    }

    public void getAppContext(){
        this.appContext = ApplicationContextProvider.getInstance().getApplicationContext();
        this.mongoOperation = (MongoOperations) appContext.getBean("mongoTemplate");
    }

    public static CustomerServiceImpl getInstance() {
        if (instance == null) {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:**/mongodb-example-spring-config.xml");
            ApplicationContextProvider.getInstance().setApplicationContext(ctx);
            instance = (CustomerServiceImpl) ctx.getBean("customerServiceImpl");
            instance.getAppContext();
        }

        return instance;
    }
}
