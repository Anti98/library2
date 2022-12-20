package com.example.library2.repositiory;

import com.example.library2.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
