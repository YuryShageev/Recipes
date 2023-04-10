package me.shageyev.recipes.services;

import me.shageyev.recipes.exceptions.FileProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.files}")
    private String dataFilePath;



    @Override
    public boolean saveToFile(String json, String dataFileName) {

        try {
            cleanDataFile(dataFileName);
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile(String dataFileName) {
        if (Files.exists(Path.of(dataFilePath, dataFileName))) {
            try {
                return Files.readString(Path.of(dataFilePath, dataFileName));
            } catch (IOException e) {
                throw new FileProcessingException("Файл не прочитан");
            }
        } else {
            return "{ }";
        }
    }

    @Override
    public File getDataFile(String dataFileName) {
        return new File(dataFilePath + "/" + dataFileName);
    }

    @Override
    public Path createFile(String suffix) throws IOException {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile(String dataFileName) {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
