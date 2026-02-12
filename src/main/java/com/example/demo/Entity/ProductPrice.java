package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "product_prices")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    public ProductPrice() {}

    public ProductPrice(Double price, Product product, Shop shop) {
        this.price = price;
        this.product = product;
        this.shop = shop;
    }

    public Long getId() { return id; }
    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Shop getShop() { return shop; }
    public void setShop(Shop shop) { this.shop = shop; }
}
