package com.example.library2.model.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorShortDTO {
    private Long id;
    private String name;
    private String lastName;
    private String secondName;
}
