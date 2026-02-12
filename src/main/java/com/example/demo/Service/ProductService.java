package com.example.demo.Service;

import com.example.demo.Entity.GroceryList;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.GroceryListRepository;
import com.example.demo.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final GroceryListRepository groceryListRepository;

    public ProductService(ProductRepository productRepository,
                          GroceryListRepository groceryListRepository) {
        this.productRepository = productRepository;
        this.groceryListRepository = groceryListRepository;
    }


    public List<Product> getProductsByList(Long listId){
        return productRepository.findByGroceryListId(listId);
    }

    public List<Product> getProductsByCategory(Long listId, String categoryName){
        return productRepository.findByGroceryListIdAndCategoryNameIgnoreCase(listId, categoryName);
    }

    public Product createProduct(Long listId, Product product){
        GroceryList list = groceryListRepository.findById(listId).orElseThrow(() -> new RuntimeException("List not found"));

        product.setGroceryList(list);

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product toggleChecked(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setChecked(!product.getChecked());
        return productRepository.save(product);
    }

}
