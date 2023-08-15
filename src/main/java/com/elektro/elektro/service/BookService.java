package com.elektro.elektro.service;

import java.util.List;

import com.elektro.elektro.model.Book;
import com.elektro.elektro.model.Professor;


public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book saveBook(Book book);
    void deleteBookById(Long id);
    Book addBook(String name, List<Professor> professor);
}
