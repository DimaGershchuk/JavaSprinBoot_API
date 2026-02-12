package com.example.demo.Controller;

import com.example.demo.DTO.ShopProductPriceDTO;
import com.example.demo.Service.ProductPriceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shops")
public class ProductPriceController {
    private final ProductPriceService productPriceService;

    public ProductPriceController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @GetMapping("/{shopId}/prices")
    public List<ShopProductPriceDTO> getPricesForList(@PathVariable Long shopId, @RequestParam Long listId){
        return productPriceService.getPricesForList(shopId, listId);
    }
}
