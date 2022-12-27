package com.example.library2.model.dto.book;

import lombok.Data;

@Data
public class NewBookShortDto {
    String title;
    String edition;
    Long  authorId;
}
