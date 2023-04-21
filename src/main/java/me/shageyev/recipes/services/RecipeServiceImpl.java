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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static final List<Ingredients> ingredientsList = new ArrayList<>();
    private static final List<String> cookingSteps = new ArrayList<>();
    private final FilesService filesService;
    private static Integer recipeId = 0;

    @Value("${name.of.recipe.file}")
    String dataFileNameRecipe;

    @Override
    public String getDataFileNameRecipe() {
        return dataFileNameRecipe;
    }

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(recipeId + 1, recipe);
        saveToFile();
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
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToFile(json, dataFileNameRecipe);
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Не удалось сохранить");
        }
    }

    private void readFromFile() throws JsonProcessingException {
        try {
            String json = filesService.readFromFile(dataFileNameRecipe);
            List<Recipe> recipeList = new ObjectMapper().readValue(json, new TypeReference<List<Recipe>>() {
            });
            for (Recipe recipe: recipeList) {
                recipeMap.put(++recipeId, recipe);
            }
        } catch (JsonProcessingException e) {
            throw e;
        }
    }


    @Override
    public void getRecipeDataFile() {
        filesService.getDataFile(dataFileNameRecipe);

    }

    @Override
    public Path createEditedRecipeFile() throws IOException {
//        List<Recipe> allRecipes = new ArrayList<Recipe>(recipeMap.values());
        Path path = filesService.createFile("recipeEditedFile");
        for (Recipe recipe : recipeMap.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getName() + "\r\n" + "Время приготовления: " + recipe.getCookingTime() + " " + recipe.getTime() + ". " + "\r\n"
                        + recipe.getIngredients() + "\r\n" + "Инструкция приготовления: " + "\r\n" + recipe.getSteps());
            }
        }
        return path;
    }

//    public void downloadTxtFilesFromOutputStream(OutputStream outputStream) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
//            String line = writer.;
//        }
//    }
}
