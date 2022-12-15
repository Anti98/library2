package com.example.library2.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {
    @GetMapping()
    public String getBook() {
        return "Book";
    }
}
