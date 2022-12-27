package com.example.library2.model.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookListDTO {
    List<BookAuthorShortDTO> bookList;
}
