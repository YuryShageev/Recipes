package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;

public interface IngredientsService {
    void  addIngredients(Ingredients ingredients);

    void getIngredients(long ingredientId);
}
