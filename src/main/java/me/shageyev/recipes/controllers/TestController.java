package me.shageyev.recipes.controllers;

import me.shageyev.recipes.services.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/test")
public class TestController {
    private final CounterService counterService;


    public TestController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping()
    public String showHelloWorld() {
        return "Hello, world";
    }

    @GetMapping("/counter")
    public String showCounter() {
        return "Количество запросов: " + counterService.getRequestCount();
    }

    @GetMapping("/greetings")
    public String showGreetings(@RequestParam String name, @RequestParam String lastName) {
        return "Hello, " + lastName + " " + name + "!";
    }
}
