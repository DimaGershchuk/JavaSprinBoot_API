package com.example.demo.Repository;

import com.example.demo.Entity.GroceryList;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {
    List<GroceryList> findByUserUsername(String username);

}
