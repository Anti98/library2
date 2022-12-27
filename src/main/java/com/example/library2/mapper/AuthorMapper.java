package com.example.library2.mapper;

import com.example.library2.model.dto.author.AuthorDTO;
import com.example.library2.model.dto.author.NewAuthorDTO;
import com.example.library2.model.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO entityToDto(AuthorEntity authorEntity);
    AuthorEntity postDtoToEntity(NewAuthorDTO newAuthorDTO);
}
