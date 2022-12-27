package com.example.library2.mapper;

import com.example.library2.model.dto.book.BookDTO;
import com.example.library2.model.dto.book.BookGetDTO;
import com.example.library2.model.dto.book.BookPostShortDto;
import com.example.library2.model.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO entityToDto(BookEntity bookEntity);

    BookGetDTO entityToBookGetDto(BookEntity bookEntity);

    BookEntity shortPostDtoToEntity(BookPostShortDto bookPostShortDto);

}
