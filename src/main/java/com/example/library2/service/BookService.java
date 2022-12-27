package com.example.library2.service;

import com.example.library2.exception.NoEntityException;
import com.example.library2.mapper.BookMapper;
import com.example.library2.model.dto.book.BookGetDTO;
import com.example.library2.model.dto.book.BookPostShortDto;
import com.example.library2.model.entity.AuthorEntity;
import com.example.library2.model.entity.BookEntity;
import com.example.library2.repositiory.AuthorRepository;
import com.example.library2.repositiory.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookMapper bookMapper;

    public BookGetDTO getBookById(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NoEntityException("No book with that id"));
        return bookMapper.entityToBookGetDto(bookEntity);
    }

    public List<BookGetDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::entityToBookGetDto)
                .collect(Collectors.toList());
    }

    public BookGetDTO postBook(BookPostShortDto bookPostShortDto) {
        AuthorEntity authorEntity = authorRepository.findById(bookPostShortDto.getAuthorId()).orElseThrow(() -> new NoEntityException("No such author"));
        BookEntity bookEntity = bookMapper.shortPostDtoToEntity(bookPostShortDto);
        bookEntity.setAuthor(authorEntity);
        return bookMapper.entityToBookGetDto(bookRepository.save(bookEntity));
    }

    public BookGetDTO deleteBook(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NoEntityException("No book with that id"));
        bookRepository.deleteById(id);
        return bookMapper.entityToBookGetDto(bookEntity);
    }
}
