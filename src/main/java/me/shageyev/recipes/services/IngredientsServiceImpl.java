package me.shageyev.recipes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.shageyev.recipes.exceptions.FileProcessingException;
import me.shageyev.recipes.model.Ingredients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    private static Map<Integer, Ingredients> ingredient = new TreeMap<>();

    private final FilesService filesService;
    private static Integer ingredientId = 0;

    @Value("${name.of.ingredient.file}")
    private String dataFileNameIngredient;
@Override
    public String getDataFileNameIngredient() {
        return dataFileNameIngredient;
    }

    public IngredientsServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }


    @Override
    public Ingredients addIngredient(Ingredients ingredients) {
        ingredient.put(ingredientId, ingredients);
        saveToFile();
        ingredientId = ++ingredientId;
        return ingredients;
    }

    @Override
    public Ingredients getIngredients(int ingredientId) {
        return ingredient.get(ingredientId);
    }

    @Override
    public Collection<Ingredients> getAllIngredients() {
        return ingredient.values();
    }

    @Override
    public Ingredients editIngredientsById(int id, Ingredients ingredients) {
        for (Map.Entry<Integer, Ingredients> ingredientsEntry : ingredient.entrySet()) {
            if (ingredientsEntry.getKey().equals(id)) {
                ingredient.put(id, ingredients);
                saveToFile();
                return ingredient.get(id);
            }
        }
        return null;
    }

    @Override
    public boolean deleteIngredientsById(int id) {
        for (Map.Entry<Integer, Ingredients> ingredientsEntry : ingredient.entrySet()) {
            if (ingredientsEntry.getKey().equals(id)) {
                ingredient.remove(id);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredient);
            filesService.saveToFile(json, dataFileNameIngredient);
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Не удалось сохранить");
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile(dataFileNameIngredient);
            ingredient = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredients>>() {
            });
        } catch (JsonProcessingException e) {
            throw new FileProcessingException("Не удалось прочитать");
        }
    }

    @Override
    public void getIngredientDataFile() {
        filesService.getDataFile(dataFileNameIngredient);
    }


}
