package sk.mongodb.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.mongodb.example.dao.ShopDAO;
import sk.mongodb.example.entity.Shop;
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

    public Shop findShopById(String id){
        Shop shop = repository.findOne(id);
        return shop;
    }


}
