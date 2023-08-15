package com.elektro.elektro.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elektro.elektro.model.Book;
import com.elektro.elektro.model.Professor;
import com.elektro.elektro.repository.BookRepository;
import com.elektro.elektro.service.BookService;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;


    
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
        
    }

    @Override
    public Book addBook(String name, List<Professor> professor) {
        Book book = new Book(name);
        book.getProfessors().addAll(professor);
        return bookRepository.save(book);

    }
    
}
