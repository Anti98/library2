package com.example.library2.model.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBookShortDto {
    private String title;
    private String edition;
    private Long  authorId;
    private Integer pageCount;
}
