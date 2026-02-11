package com.example.demo.Repository;

import com.example.demo.Entity.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {

}
