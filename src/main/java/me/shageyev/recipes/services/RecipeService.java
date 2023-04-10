package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

public interface RecipeService {


    String getDataFileNameRecipe();

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int recipeId);

    Collection<Recipe> getAllRecipes();

    Recipe editRecipeById(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    void addIngredientsToRecipe(Ingredients ingredients);

    void getRecipeDataFile();

    Path createEditedRecipeFile() throws IOException;
}
