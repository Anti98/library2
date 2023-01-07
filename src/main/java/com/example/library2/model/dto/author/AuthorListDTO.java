package com.example.library2.model.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorListDTO {
   private List<AuthorDTO> authors;
}
