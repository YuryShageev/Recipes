package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static final Map<Integer, Recipe> recipeMap = new TreeMap<>();
    private static final List<Ingredients> ingredientsList = new ArrayList<>();
    private static final List<String> cookingSteps = new ArrayList<>();
    private static Integer recipeId = 0;
    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(recipeId++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(int recipeId) {
        if (recipeMap.get(recipeId) == null) {
            throw new IllegalArgumentException("Рецепт ещё не добавлен");
        } else {
            return recipeMap.get(recipeId);
        }
    }

    @Override
    public Collection<Recipe> getAllRecipes() {

        return recipeMap.values();
    }

    @Override
    public Recipe editRecipeById(int id, Recipe recipe) {
        for (Map.Entry<Integer, Recipe> recipeEntry : recipeMap.entrySet()) {
            if (recipeEntry.getKey().equals(id)) {
                recipeMap.put(id, recipe);
                return recipeMap.get(id);
            }
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        for (Map.Entry<Integer, Recipe> recipeEntry : recipeMap.entrySet()) {
            if (recipeEntry.getKey().equals(id)) {
                recipeMap.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addIngredientsToRecipe(Ingredients ingredient) {
         ingredientsList.add(ingredient);
    }
}
