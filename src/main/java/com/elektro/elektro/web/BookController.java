package com.elektro.elektro.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.elektro.elektro.model.Book;
import com.elektro.elektro.model.Professor;
import com.elektro.elektro.service.BookService;
import com.elektro.elektro.service.ProfessorService;

@Controller
public class BookController {
    public BookService bookService;
    public ProfessorService professorService;

    public BookController(BookService bookService, ProfessorService professorService) {
        this.bookService = bookService;
        this.professorService = professorService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("book", books);
        return "books"; 
    }

    @GetMapping("/books/new")
    public String createBookForm(Model model){
        Book books = new Book();
        List<Professor> professors = professorService.getAllProfessors();

        // Add the professors to the model
        model.addAttribute("professors", professors);
        model.addAttribute("book", books);
        return "create_books";
    }

    @PostMapping("/books")
    public String saveBook(
        @RequestParam String name,
        @RequestParam(required = false) List<Long> professorIds,
        Model model,
        @ModelAttribute("book") Book book,
        @RequestParam("imageFile") MultipartFile imageFile
    ) {
        if (!imageFile.isEmpty()) {
            try {
                // Save the file to a location (e.g., resources/static/images/)
                String imageFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path imagePath = Paths.get("src/main/resources/static/images/" + imageFileName);
                Files.write(imagePath, imageFile.getBytes());
    
                // Set the image URL in the Book object
                book.setPictureUrl("/images/" + imageFileName);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
    
        // Set other attributes for the book
        book.setName(name);
    
        if (professorIds != null && !professorIds.isEmpty()) {
            List<Professor> selectedProfessors = professorService.findProfessorsByIds(professorIds);
            book.setProfessors(new HashSet<>(selectedProfessors));
        } else {
            book.setProfessors(null); // Empty set of professors
        }
    
        // Save the book
        bookService.saveBook(book);
    
        return "redirect:/books";
    }
    
    

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
}

}
