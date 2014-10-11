package sk.mongodb.example.service;

import sk.mongodb.example.entity.Shop;

/**
 * Created by Silvia on 11. 10. 2014.
 */
public interface ShopService {
    public void saveShop(String id, String name, String address);
    public Shop findShopById(String id);
}
