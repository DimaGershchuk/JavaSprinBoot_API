package com.example.demo.DTO;

public class ShopProductPriceDTO {
    private String productName;
    private Double price;

    public ShopProductPriceDTO(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() { return productName; }
    public Double getPrice() { return price; }
}
