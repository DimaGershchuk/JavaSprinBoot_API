package com.example.demo.Controller;
import com.example.demo.Entity.Recipe;
import com.example.demo.Service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @PostMapping("/{recipeId}/add-to-list/{listId}")
    public void addRecipeToGroceryList(@PathVariable Long recipeId, @PathVariable Long listId){
        recipeService.addIngredientsToList(recipeId, listId);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id,
                               @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
