package com.example.library2.controller;

import com.example.library2.model.dto.book.BookAuthorShortDTO;
import com.example.library2.model.dto.book.BookListDTO;
import com.example.library2.model.dto.book.BookNewShortDto;
import com.example.library2.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public BookAuthorShortDTO getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    @Operation(summary = "Получение всех книг", description = "Получение всех книг")
    public BookListDTO getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Добавление книги", description = "Добавление книги")
    public BookAuthorShortDTO postBook(@RequestBody BookNewShortDto bookNewShortDto) {
        return bookService.postBook(bookNewShortDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Удаление книги", description = "Удаление книги по id")
    public BookAuthorShortDTO deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
}
