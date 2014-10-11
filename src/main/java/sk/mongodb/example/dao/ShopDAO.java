package sk.mongodb.example.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import sk.mongodb.example.entity.Shop;

/**
 * Created by Silvia on 11. 10. 2014.
 */
public interface ShopDAO extends PagingAndSortingRepository<Shop, String> {
}
