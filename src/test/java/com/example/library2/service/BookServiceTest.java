package com.example.library2.service;

import com.example.library2.exception.NoEntityException;
import com.example.library2.mapper.AuthorMapperImpl;
import com.example.library2.mapper.BookMapperImpl;
import com.example.library2.repositiory.AuthorRepository;
import com.example.library2.repositiory.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.example.library2.service.TestValues.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BookServiceTest {
    @Spy
    private AuthorMapperImpl authorMapper;
    @Spy
    private BookMapperImpl bookMapper;
    @Mock
    private BookRepository bookRepositoryMock;
    @Mock
    private AuthorRepository authorRepositoryMock;
    @InjectMocks
    private BookService bookService;

    @Test
    @DisplayName("Получение книги по id")
    void getBookById() {
        Mockito.when(bookRepositoryMock.findById(1L)).thenReturn(Optional.of(bookEntity));
        assertEquals(bookAuthorShortDTO, bookService.getBookById(1L));
    }
    @Test
    @DisplayName("вызов ошибки при получении книги с неверным id")
    void getBookWrongId() {
        Mockito.when(bookRepositoryMock.findById(-1L)).thenReturn(Optional.empty());
        assertThrows(NoEntityException.class, () -> bookService.getBookById(-1L));
    }

    @Test
    @DisplayName("получение всех книг")
    void getAllBooks() {
        Mockito.when(bookRepositoryMock.findAll()).thenReturn(allBooksEntityList);
        assertEquals(bookListDTO, bookService.getAllBooks());
    }

    @Test
    @DisplayName("Создание книги")
    void postBook() {
        Mockito.when(authorRepositoryMock.findById(1L)).thenReturn(Optional.of(authorEntity));
        Mockito.when(bookRepositoryMock.save(bookEntitySave)).thenReturn(bookEntity);
        assertEquals(bookAuthorShortDTO, bookService.postBook(newBookShortDto));
    }

    @Test
    @DisplayName("вызов ошибки при создании книги с неверным id автора")
    void createBookWrongAuthorId() {
        Mockito.when(authorRepositoryMock.findById(-1L)).thenReturn(Optional.empty());
        assertThrows(NoEntityException.class, () -> bookService.postBook(bookWrongAuthorDTO));
    }
    @Test
    @DisplayName("удаление автора")
    void deleteBook() {
        Mockito.when(bookRepositoryMock.findById(1L)).thenReturn(Optional.of(bookEntity));
        assertEquals(bookAuthorShortDTO, bookService.deleteBook(1L));
    }
    @Test
    @DisplayName("вызов ошибки при удалении книги с неверным id")
    void deleteBookWrongId() {
        Mockito.when(bookRepositoryMock.findById(-1L)).thenReturn(Optional.empty());
        assertThrows(NoEntityException.class, () -> bookService.deleteBook(-1L));
    }
}