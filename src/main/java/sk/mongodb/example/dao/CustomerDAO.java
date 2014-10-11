package sk.mongodb.example.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.mongodb.example.entity.Customer;

/**
 * Created by Silvia on 8. 10. 2014.
 */
public interface CustomerDAO extends PagingAndSortingRepository<Customer, String> {
}
