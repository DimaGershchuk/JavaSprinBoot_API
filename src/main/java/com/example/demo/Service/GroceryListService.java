package com.example.demo.Service;

import com.example.demo.Entity.GroceryList;
import com.example.demo.Repository.GroceryListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryListService {
    private final GroceryListRepository repository;

    public GroceryListService(GroceryListRepository repository) {
        this.repository = repository;
    }

    public List<GroceryList> getAll() {
        return repository.findAll();
    }

    public GroceryList getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("List not found"));
    }

    public GroceryList create(GroceryList groceryList) {
        if (groceryList.getName() == null || groceryList.getName().isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }

        return repository.save(groceryList);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
