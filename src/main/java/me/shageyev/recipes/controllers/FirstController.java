package me.shageyev.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirstController {

@GetMapping
public String launchInfo(){
    return "Приложение запущено";
}

}
