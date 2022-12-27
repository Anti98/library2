package com.example.library2.model.dto.book;

import lombok.Data;

@Data
public class BookPostShortDto {
    String title;
    String edition;
    Long  authorId;
}
