package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<ProductPrice> productPrices = new ArrayList<>();

    public Shop() {}

    public Shop(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<ProductPrice> getProductPrices() {
        return productPrices;
    }
}
