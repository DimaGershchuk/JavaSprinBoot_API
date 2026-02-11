package com.example.demo.Controller;

import com.example.demo.Entity.GroceryList;
import com.example.demo.Repository.GroceryListRepository;
import com.example.demo.Service.GroceryListService;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery-lists")
public class GroceryListController {

    private final GroceryListService service;

    public GroceryListController(GroceryListService service){
        this.service = service;
    }

    @GetMapping
    public List<GroceryList> getAll() {
        return service.getAll();
    }

    @PostMapping
    public GroceryList create(@RequestBody GroceryList groceryList){
        return service.create(groceryList);
    }

    @GetMapping("/{id}")
    public GroceryList getByid(@PathVariable Long id){
        return  service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
