package me.shageyev.recipes.services;

import me.shageyev.recipes.model.Ingredients;

import java.util.Collection;
import java.util.List;

public interface IngredientsService {




    Ingredients addIngredient(Ingredients ingredients);

    Ingredients getIngredients(int ingredientId);

    Collection<Ingredients> getAllIngredients();


    Ingredients editIngredientsById(int id, Ingredients ingredients);

    boolean deleteIngredientsById(int id);
}
