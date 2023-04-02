package me.shageyev.recipes.services;

import java.io.File;

public interface FilesService {
    boolean saveToFile(String json, String name);

    String readFromFile( String name);

    File getDataFile(String dataFileName);

    boolean cleanDataFile(String dataFileName);
}
