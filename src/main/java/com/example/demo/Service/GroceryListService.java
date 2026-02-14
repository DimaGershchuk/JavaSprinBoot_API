package com.example.demo.Service;

import com.example.demo.Entity.GroceryList;
import com.example.demo.Entity.User;
import com.example.demo.Repository.GroceryListRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryListService {
    private final GroceryListRepository repository;
    private final UserRepository userRepository;

    public GroceryListService(GroceryListRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<GroceryList> getAll() {
        return repository.findAll();
    }

    public List<GroceryList> getUserLists(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUserUsername(username);
    }

    public GroceryList getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("List not found"));
    }

    public GroceryList create(GroceryList groceryList) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        groceryList.setUser(user);

        return repository.save(groceryList);
    }

    public void rename(Long id, String newName){
        GroceryList groceryList = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        groceryList.setName(newName);

        repository.save(groceryList);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
