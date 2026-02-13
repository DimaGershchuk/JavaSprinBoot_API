package com.example.demo.Service;

import com.example.demo.Entity.GroceryList;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.Recipe;
import com.example.demo.Entity.RecipeIngredient;
import com.example.demo.Repository.GroceryListRepository;
import com.example.demo.Repository.RecipeRepository;
import org.apache.commons.logging.Log;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final GroceryListRepository groceryListRepository;

    public RecipeService(RecipeRepository recipeRepository, GroceryListRepository groceryListRepository) {
        this.recipeRepository = recipeRepository;
        this.groceryListRepository = groceryListRepository;
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAllWithIngredients();
    }

    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public List<Recipe> getUserRecipes() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return recipeRepository.findByUserUsername(username);
    }

    public Recipe createRecipe(Recipe recipe) {
        for (RecipeIngredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }

        return recipeRepository.save(recipe);
    }

    public void addIngredientsToList(Long recipeId, Long listID){
        Recipe recipe = recipeRepository.findByIdWithIngredients(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));
        GroceryList groceryList = groceryListRepository.findById(listID).orElseThrow(() -> new RuntimeException("Recipe not found"));

        for (RecipeIngredient ingredient : recipe.getIngredients()){
            Product product = new Product();

            product.setName(ingredient.getName());
            product.setChecked(false);
            product.setGroceryList(groceryList);

            groceryList.getProducts().add(product);
        }

        groceryListRepository.save(groceryList);
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe){
        Recipe recipe = getRecipeById(id);

        recipe.setName(updatedRecipe.getName());
        recipe.setCookingTime(updatedRecipe.getCookingTime());
        recipe.setImageUrl(updatedRecipe.getImageUrl());

        return recipeRepository.save(recipe);
    }

    public List<Recipe> getFavoriteRecipes() {
        return recipeRepository.findAllFavorites();
    }

    public Recipe toggleFavorite(Long id) {
        Recipe recipe = getRecipeById(id);
        recipe.setFavorite(!recipe.getFavorite());
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
