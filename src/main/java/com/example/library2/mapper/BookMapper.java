package com.example.library2.mapper;

import com.example.library2.model.dto.book.BookDTO;
import com.example.library2.model.dto.book.BookShortDTO;
import com.example.library2.model.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity dtoToEntity(BookDTO bookDTO);

    BookDTO entityToDto(BookEntity bookEntity);

    BookEntity bookShortDtoToEntity(BookShortDTO bookShortDTO);

    BookShortDTO entityToBookShortDto(BookEntity bookEntity);
}
