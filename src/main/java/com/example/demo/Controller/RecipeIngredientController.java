package com.example.demo.Controller;

import com.example.demo.Entity.RecipeIngredient;
import com.example.demo.Service.RecipeIngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipe-ingredients")
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;

    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @GetMapping
    public List<RecipeIngredient> getAllRecipeIngredients() {
        return recipeIngredientService.getAllRecipeIngredients();
    }

    @GetMapping("/{id}")
    public RecipeIngredient getRecipeIngredient(@PathVariable Long id) {
        return recipeIngredientService.getRecipeIngredientsById(id);
    }

    @GetMapping("/recipe/{recipeId}")
    public List<RecipeIngredient> getByRecipe(@PathVariable Long recipeId) {
        return recipeIngredientService.getIngredientsByRecipe(recipeId);
    }

    @PostMapping
    public RecipeIngredient createRecipeIngredient(
            @RequestBody RecipeIngredient recipeIngredient) {
        return recipeIngredientService.createRecipeIngredient(recipeIngredient);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipeIngredient(@PathVariable Long id) {
        recipeIngredientService.deleteRecipeIngredients(id);
    }
}
