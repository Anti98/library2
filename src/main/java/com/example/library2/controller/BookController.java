package com.example.library2.controller;

import com.example.library2.model.dto.book.BookDTO;
import com.example.library2.model.dto.book.BookShortDTO;
import com.example.library2.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
@Tag(name = "Книги", description = "API для работы с книгами")
@SecurityRequirement(name = "bearer-key")
public class BookController {
    BookService bookService;

    @GetMapping("/{id}")
    @Operation(summary = "Получение книги по id", description = "Получение книги по id")
    public BookDTO getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех книг", description = "Получение всех книг")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @Operation(summary = "Добавление книги", description = "Добавление книги")
    public BookDTO postBook(@RequestBody BookShortDTO bookDTO) {
        return bookService.postBook(bookDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление книги", description = "Удаление книги по id")
    public BookDTO deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
}
