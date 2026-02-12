package com.example.demo.Service;

import com.example.demo.DTO.ShopProductPriceDTO;
import com.example.demo.Repository.ProductPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPriceService {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceService(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    public List<ShopProductPriceDTO> getPricesForList(Long shopId, Long listId){
        return productPriceRepository.getPricesForListInShop(shopId, listId);
    }
}
