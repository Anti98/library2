package com.example.library2.service;

import com.example.library2.exception.NoEntityException;
import com.example.library2.mapper.BookMapper;
import com.example.library2.model.dto.book.BookDTO;
import com.example.library2.model.dto.book.BookShortDTO;
import com.example.library2.model.entity.BookEntity;
import com.example.library2.repositiory.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;

    public BookDTO getBookById(Long id) {
        return bookMapper.entityToDto(bookRepository.findById(id).orElseThrow(() -> new NoEntityException("No book with that id")));
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::entityToDto).collect(Collectors.toList());
    }

    public BookDTO postBook(BookShortDTO bookDTO) {
        return bookMapper.entityToDto(bookRepository.save(bookMapper.bookShortDtoToEntity(bookDTO)));
    }

    public BookDTO deleteBook(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(() -> new NoEntityException("No book with that id"));
        bookRepository.deleteById(id);
        return bookMapper.entityToDto(bookEntity);
    }
}
