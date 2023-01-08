package com.example.library2.model.dto.author;

import com.example.library2.model.dto.book.BookShortDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String lastName;
    private String secondName;
    private LocalDate birthDate;
    private Set<BookShortDTO> books;
}