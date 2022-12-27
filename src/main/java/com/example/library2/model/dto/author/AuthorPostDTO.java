package com.example.library2.model.dto.author;

import com.example.library2.model.dto.book.BookShortDTO;
import lombok.Data;

import java.util.Set;
@Data
public class AuthorPostDTO {
    private String name;
    private String lastName;
    private String secondName;
}
