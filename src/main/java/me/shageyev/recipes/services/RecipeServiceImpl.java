package me.shageyev.recipes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.shageyev.recipes.exceptions.FileProcessingException;
import me.shageyev.recipes.model.Ingredients;
import me.shageyev.recipes.model.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap = new TreeMap<>();
    private static final List<Ingredients> ingredientsList = new ArrayList<>();
    private static final List<String> cookingSteps = new ArrayList<>();
    private final FilesService filesService;
    private static Integer recipeId = 0;

    @Value("${name.of.recipe.file}")
    String dataFileNameRecipe;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(recipeId, recipe);
        saveToFile();
        recipeId = ++recipeId;
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
                saveToFile();
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
                saveToFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public void addIngredientsToRecipe(Ingredients ingredient) {
        ingredientsList.add(ingredient);
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json, dataFileNameRecipe);
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Не удалось сохранить");
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile(dataFileNameRecipe);
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Файл не прочитан");
        }
    }
}
