package com.elektro.elektro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elektro.elektro.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
