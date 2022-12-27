package com.example.library2.service;

import com.example.library2.exception.NoEntityException;
import com.example.library2.mapper.AuthorMapperImpl;
import com.example.library2.model.entity.AuthorEntity;
import com.example.library2.repositiory.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static com.example.library2.service.TestValues.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class AuthorServiceTest {
    @Spy
    private AuthorMapperImpl authorMapper;
    @Mock
    private AuthorRepository authorRepositoryMock;
    @InjectMocks
    private AuthorService authorService;

    @Test
    @DisplayName("получение автора пo id")
    void getAuthorByID() {
        Mockito.when(authorRepositoryMock.findById(1L)).thenReturn(Optional.of(authorEntity));
        assertEquals(authorDTO, authorService.getAuthorByID(1L));
    }

    @Test
    @DisplayName("вызов ошибки при попытке получить автора с неправильным id")
    void getAuthorWrongId() {
        Mockito.when(authorRepositoryMock.findById(-1L)).thenReturn(Optional.empty());
        assertThrows(NoEntityException.class, () -> authorService.getAuthorByID(-1L));
    }

    @Test
    @DisplayName("Создание автора")
    void postAuthor() {
        Mockito.when(authorRepositoryMock.save(authorEntitySave)).thenReturn(authorEntity);
        assertEquals(authorDTO, authorService.postAuthor(newAuthorDTO));
    }

    @Test
    @DisplayName("получение списка всех авторов")
    void getAllAuthors() {
        ArrayList<AuthorEntity> authorEntityList = new ArrayList<>(Collections.singletonList(authorEntity));
        Mockito.when(authorRepositoryMock.findAll()).thenReturn(authorEntityList);
        assertEquals(authorListDTO, authorService.getAllAuthors());
    }

    @Test
    @DisplayName("удаление автора по id")
    void deleteAuthorById() {
        Mockito.when(authorRepositoryMock.findById(1L)).thenReturn(Optional.of(authorEntity));
        assertEquals(authorDTO, authorService.deleteAuthorById(1L));
    }

    @Test
    @DisplayName("вызов ошибки при попытке удалить автора с неправильным id")
    void deleteAuthorWrongId() {
        Mockito.when(authorRepositoryMock.findById(-1L)).thenReturn(Optional.empty());
        assertThrows(NoEntityException.class, () -> authorService.deleteAuthorById(-1L));
    }
}