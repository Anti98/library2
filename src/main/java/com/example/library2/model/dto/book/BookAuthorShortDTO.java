package com.example.library2.model.dto.book;

import com.example.library2.model.dto.author.AuthorShortDTO;
import lombok.Data;

@Data
public class BookAuthorShortDTO {
    private Long id;
    private String title;
    private String edition;
    private AuthorShortDTO author;
}
