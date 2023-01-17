package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Recipe;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    void getRecipe(long recipeId);
}
