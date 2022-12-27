package com.example.library2.model.dto.author;

import com.example.library2.model.dto.book.BookShortDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String lastName;
    private String secondName;
    private Set<BookShortDTO> books;
}