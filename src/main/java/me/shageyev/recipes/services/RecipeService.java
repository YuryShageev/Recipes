package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;

import java.util.Collection;
import java.util.Map;

public interface RecipeService {


    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int recipeId);

    Collection<Recipe> getAllRecipes();

    Recipe editRecipeById(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    void addIngredientsToRecipe(Ingredients ingredients);
}
