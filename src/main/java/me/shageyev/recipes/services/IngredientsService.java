package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;

public interface IngredientsService {

    void addIngredients(String name);

    void getIngredients(long ingredientId);
}
