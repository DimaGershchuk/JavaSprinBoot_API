package com.example.demo.Service;

import com.example.demo.Entity.Shop;
import com.example.demo.Repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> getAllShops(){
        return shopRepository.findAll();
    }

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }
}
