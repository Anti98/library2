package com.example.library2.model.dto.book;

import lombok.Data;

@Data
public class BookShortDTO {
    private Long id;
    private String title;
    private String edition;
    private Integer pageCount;
}
