package me.shageyev.recipes.services;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
