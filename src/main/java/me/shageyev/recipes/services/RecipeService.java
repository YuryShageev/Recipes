package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;

import java.util.Map;

public interface RecipeService {


    void addRecipe(String name, int cookingTime);

    Recipe getRecipe(int recipeId);

    Map<Integer, Recipe> getAllRecipes();

    void addIngredientsToRecipe(Ingredients ingredients);
}
