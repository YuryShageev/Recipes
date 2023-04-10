package me.shageyev.recipes.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FilesService {
    boolean saveToFile(String json, String name);

    String readFromFile( String name);

    File getDataFile(String dataFileName);

    Path createFile(String suffix) throws IOException;

    boolean cleanDataFile(String dataFileName);
}
