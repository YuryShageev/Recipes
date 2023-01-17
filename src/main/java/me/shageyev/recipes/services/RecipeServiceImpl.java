package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Recipe;

import java.util.Map;
import java.util.TreeMap;

public class RecipeServiceImpl implements RecipeService {

    private static final Map<Long, Recipe> recipeMap = new TreeMap<>();
    private static long recipeId = 0;
    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(recipeId++, recipe);
    }

    @Override
    public void getRecipe(long recipeId) {
        System.out.println(recipeMap.get(recipeId));
    }
}
