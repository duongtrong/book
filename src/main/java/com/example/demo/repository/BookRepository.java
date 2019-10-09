package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Override
    <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable);

//    @Query("select b from Book as a where a.status = :status")
//    Page<Book> findAllByBookAndTitle(@Param("status") int status, @Param("description") String description, Pageable pageable);

}
