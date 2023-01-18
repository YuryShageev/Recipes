package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Recipe;

import java.util.Map;

public interface RecipeService {


    void addRecipe(Recipe recipe);

    Recipe getRecipe(int recipeId);

    Map getAllRecipes();
}
