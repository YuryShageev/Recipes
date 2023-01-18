package me.shageyev.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private List<Ingredients> ingredients;
    private List<String> cookingSteps;
}
