package com.example.library2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="author_entity_id_seq")
    @SequenceGenerator(name="author_entity_id_seq",sequenceName="author_entity_id_seq", allocationSize=1)
    private Long id;
    private String name;
    private String lastName;
    private String secondName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<BookEntity> books;
}