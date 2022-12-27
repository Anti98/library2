package com.example.library2.model.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBookShortDto {
    String title;
    String edition;
    Long  authorId;
}
