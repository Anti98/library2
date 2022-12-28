package com.example.library2.service;

import com.example.library2.model.dto.author.AuthorDTO;
import com.example.library2.model.dto.author.AuthorListDTO;
import com.example.library2.model.dto.author.AuthorShortDTO;
import com.example.library2.model.dto.author.NewAuthorDTO;
import com.example.library2.model.dto.book.BookAuthorShortDTO;
import com.example.library2.model.dto.book.BookListDTO;
import com.example.library2.model.dto.book.NewBookShortDto;
import com.example.library2.model.entity.AuthorEntity;
import com.example.library2.model.entity.BookEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TestValues {
    final static Long ID = 1L;
    final static Long INVALID_ID = -1L;
    protected static AuthorDTO authorDTO = new AuthorDTO(ID, "AuthorName", "AuthorLastName", "AuthorSecondName", null);
    protected static AuthorEntity authorEntity = new AuthorEntity(ID, "AuthorName", "AuthorLastName", "AuthorSecondName", null);
    protected static AuthorEntity authorEntitySave = new AuthorEntity(null, "AuthorName", "AuthorLastName", "AuthorSecondName", null);
    protected static NewAuthorDTO newAuthorDTO = new NewAuthorDTO("AuthorName", "AuthorLastName", "AuthorSecondName");
    protected static NewBookShortDto newBookShortDto = new NewBookShortDto("title", "edition", ID);
    protected static NewBookShortDto bookWrongAuthorDTO = new NewBookShortDto("title", "edition", INVALID_ID);
    protected static AuthorListDTO authorListDTO = new AuthorListDTO(new ArrayList<>(Collections.singletonList(authorDTO)));
    protected static BookEntity bookEntity = new BookEntity(ID, "title", "edition", authorEntity);
    protected static BookEntity bookEntitySave = new BookEntity(null, "title", "edition", authorEntity);
    protected static AuthorShortDTO authorShortDTO = new AuthorShortDTO(ID, "AuthorName", "AuthorLastName", "AuthorSecondName");
    protected static BookAuthorShortDTO bookAuthorShortDTO = new BookAuthorShortDTO(ID, "title", "edition", authorShortDTO);
    protected static AuthorShortDTO authorShortDTO2 = new AuthorShortDTO(2L, "AuthorName2", "AuthorLastName2", "AuthorSecondName2");
    protected static AuthorEntity authorEntity2 = new AuthorEntity(2L, "AuthorName2", "AuthorLastName2", "AuthorSecondName2", null);
    protected static BookEntity bookEntity2 = new BookEntity(2L, "title2", "edition2", authorEntity);
    protected static BookEntity bookEntity3 = new BookEntity(3L, "title3", "edition3", authorEntity2);
    protected static BookAuthorShortDTO bookAuthorShortDTO2 = new BookAuthorShortDTO(2L, "title2", "edition2", authorShortDTO);
    protected static BookAuthorShortDTO bookAuthorShortDTO3 = new BookAuthorShortDTO(3L, "title3", "edition3", authorShortDTO2);
    protected static List<BookEntity> allBooksEntityList = new ArrayList<>(Arrays.asList(bookEntity, bookEntity2, bookEntity3));
    protected static List<BookAuthorShortDTO> allBooksList = new ArrayList<>(Arrays.asList(bookAuthorShortDTO, bookAuthorShortDTO2, bookAuthorShortDTO3));
    protected static BookListDTO bookListDTO = new BookListDTO(allBooksList);
}
