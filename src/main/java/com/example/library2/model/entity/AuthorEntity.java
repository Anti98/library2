package com.example.library2.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String lastName;
    private String secondName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BookEntity> books;
}