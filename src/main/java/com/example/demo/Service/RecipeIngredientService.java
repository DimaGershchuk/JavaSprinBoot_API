package com.example.demo.Service;

import com.example.demo.Entity.RecipeIngredient;
import com.example.demo.Repository.RecipeIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository){
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public List<RecipeIngredient> getAllRecipeIngredients(){
        return recipeIngredientRepository.findAll();
    }

    public List<RecipeIngredient> getIngredientsByRecipe(Long recipeId) {
        return recipeIngredientRepository.findByRecipeId(recipeId);
    }

    public RecipeIngredient getRecipeIngredientsById(Long id){
        return recipeIngredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public RecipeIngredient createRecipeIngredient(RecipeIngredient recipeIngredient){
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public void deleteRecipeIngredients(Long id) {
        recipeIngredientRepository.deleteById(id);
    }


}
