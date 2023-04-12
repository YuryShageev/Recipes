package me.shageyev.recipes.controllers;


import me.shageyev.recipes.services.FilesService;
import me.shageyev.recipes.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/recipeFiles")
public class RecipeFilesController {

    private final RecipeService recipeService;
    private final FilesService filesService;

    public RecipeFilesController(RecipeService recipeService, FilesService filesService) {
        this.recipeService = recipeService;
        this.filesService = filesService;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File file = filesService.getDataFile(recipeService.getDataFileNameRecipe());

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Transactionlog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/txtRecipe")
    public ResponseEntity<Object> downloadTxtRecipe() throws IOException {
        try {
            Path path = recipeService.createEditedRecipeFile();
            if (Files.size(path) != 0) {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
                return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN)
                        .contentLength(Files.size(path))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "- recipeList.html\"")
                        .body(resource);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        filesService.cleanDataFile(recipeService.getDataFileNameRecipe());
        File dataFile = filesService.getDataFile(recipeService.getDataFileNameRecipe());

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
