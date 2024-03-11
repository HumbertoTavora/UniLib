package com.example.bookService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/books")
    public String login() {
        return "pesquisarLivro";
    }

}
