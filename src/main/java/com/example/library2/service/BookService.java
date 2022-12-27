package com.example.library2.service;

import com.example.library2.exception.NoEntityException;
import com.example.library2.mapper.BookMapper;
import com.example.library2.model.dto.book.BookAuthorShortDTO;
import com.example.library2.model.dto.book.BookListDTO;
import com.example.library2.model.dto.book.NewBookShortDto;
import com.example.library2.model.entity.AuthorEntity;
import com.example.library2.model.entity.BookEntity;
import com.example.library2.repositiory.AuthorRepository;
import com.example.library2.repositiory.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookMapper bookMapper;

    public BookAuthorShortDTO getBookById(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NoEntityException("No book with that id"));
        return bookMapper.entityToBookGetDto(bookEntity);
    }

    public BookListDTO getAllBooks() {
        return new BookListDTO(bookRepository.findAll().stream()
                .map(bookMapper::entityToBookGetDto)
                .collect(Collectors.toList()));
    }

    public BookAuthorShortDTO postBook(NewBookShortDto newBookShortDto) {
        AuthorEntity authorEntity = authorRepository.findById(newBookShortDto.getAuthorId()).orElseThrow(() -> new NoEntityException("No such author"));
        BookEntity bookEntity = bookMapper.shortPostDtoToEntity(newBookShortDto);
        bookEntity.setAuthor(authorEntity);
        return bookMapper.entityToBookGetDto(bookRepository.save(bookEntity));
    }

    public BookAuthorShortDTO deleteBook(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NoEntityException("No book with that id"));
        bookRepository.deleteById(id);
        return bookMapper.entityToBookGetDto(bookEntity);
    }
}
