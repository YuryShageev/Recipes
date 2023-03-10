package me.shageyev.recipes.services;

public interface FilesService {
    boolean saveToFile(String json, String name);

    String readFromFile( String name);

    boolean cleanDataFile(String dataFileName);
}
