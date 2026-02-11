package com.example.demo.Repository;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
