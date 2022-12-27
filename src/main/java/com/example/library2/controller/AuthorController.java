package com.example.library2.controller;

import com.example.library2.model.dto.author.AuthorDTO;
import com.example.library2.model.dto.author.AuthorPostDTO;
import com.example.library2.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@SecurityRequirement(name = "bearer-key")
@AllArgsConstructor
@Tag(name = "Авторы", description = "API для работы с авторами")
public class AuthorController {
    AuthorService authorService;

    @GetMapping("/{id}")
    @Operation(summary = "Найти автора", description = "Найти автора по id")
    public AuthorDTO getAuthor(@PathVariable Long id) {
        return authorService.getAuthorByID(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Добавить автора", description = "Добавить автора")
    public AuthorDTO postAuthor(@RequestBody AuthorPostDTO authorPostDTO) {
        return authorService.postAuthor(authorPostDTO);
    }

    @GetMapping
    @Operation(summary = "Найти всех авторов", description = "Найти всех авторов")
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Удалить автора", description = "Удалить автора по id")
    public AuthorDTO deleteById(@PathVariable Long id) {
        return authorService.deleteAuthorById(id);
    }

}
