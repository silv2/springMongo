package sk.mongodb.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.mongodb.example.dao.ShopDAO;
import sk.mongodb.example.entity.Shop;
import sk.mongodb.example.exceptions.ShopEmptyResultException;
import sk.mongodb.example.service.ShopService;

/**
 * Created by Silvia on 11. 10. 2014.
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired(required=true)
    ShopDAO repository;

    @Override
    public void saveShop(String id, String name, String address){
        Shop shop = new Shop(id, name, address);
        repository.save(shop);
    }

    public void deleteShop(String id){
        repository.delete(id);
    }

    @Override
    public Shop findShopById(String id) throws ShopEmptyResultException{
        Shop shop = repository.findOne(id);

        if(shop == null){
            throw new ShopEmptyResultException("Method findShopById(String id) return null");
        }
        return shop;
    }

    @Override
    public Iterable<Shop> getAllShops() throws ShopEmptyResultException {
        Iterable<Shop> allCustomers = repository.findAll();
        if(allCustomers == null){
            throw new ShopEmptyResultException("Method getAllShops() return null");
        }
        return allCustomers;
    }


}
