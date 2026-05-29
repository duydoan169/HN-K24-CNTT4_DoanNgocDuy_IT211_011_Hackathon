package org.example.hackathon.repository;

import org.example.hackathon.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findBookByAuthorContainingAndTitleContaining(String search, String search2, Pageable pageable);
}
