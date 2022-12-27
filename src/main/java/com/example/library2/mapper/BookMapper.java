package com.example.library2.mapper;

import com.example.library2.model.dto.book.BookDTO;
import com.example.library2.model.dto.book.BookAuthorShortDTO;
import com.example.library2.model.dto.book.BookNewShortDto;
import com.example.library2.model.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO entityToDto(BookEntity bookEntity);

    BookAuthorShortDTO entityToBookGetDto(BookEntity bookEntity);

    BookEntity shortPostDtoToEntity(BookNewShortDto bookNewShortDto);

}
