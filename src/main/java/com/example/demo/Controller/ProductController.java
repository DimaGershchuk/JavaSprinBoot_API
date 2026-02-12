package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;
import jakarta.servlet.ServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping("grocery-lists/{listId}/products")
    public List<Product> getProducts(@PathVariable Long listId, @RequestParam(required = false) String category){

        if(category != null && !category.isEmpty()){
            return productService.getProductsByCategory(listId, category);
        }
        return productService.getProductsByList(listId);
    }

    @PostMapping("grocery-lists/{listId}/products")
    public Product createProduct(@PathVariable Long listId, @RequestBody Product product){
        return productService.createProduct(listId, product);
    }

    @DeleteMapping("products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @PatchMapping(" products/{productId}/toggle")
    public Product toggleChecked(@PathVariable Long productId) {
        return productService.toggleChecked(productId);
    }

}
