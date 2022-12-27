package com.example.library2.service;

import com.example.library2.exception.NoEntityException;
import com.example.library2.mapper.AuthorMapper;
import com.example.library2.model.dto.author.AuthorDTO;
import com.example.library2.model.dto.author.AuthorPostDTO;
import com.example.library2.model.entity.AuthorEntity;
import com.example.library2.repositiory.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    AuthorRepository authorRepository;
    AuthorMapper authorMapper;

    public AuthorDTO getAuthorByID(Long id) {
        AuthorEntity author = authorRepository.findById(id).orElseThrow(() -> new NoEntityException("No author with that id"));
        return authorMapper.entityToDto(author);
    }

    public AuthorDTO postAuthor(AuthorPostDTO authorPostDTO) {
        return authorMapper.entityToDto(authorRepository.save(authorMapper.postDtoToEntity(authorPostDTO)));
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public AuthorDTO deleteAuthorById(Long id) {
        AuthorEntity authorEntity = authorRepository.findById(id).orElseThrow(() -> new NoEntityException("No author with that id"));
        authorRepository.deleteById(id);
        return authorMapper.entityToDto(authorEntity);
    }
}
