package hr.algebra.bookify.backend.controller;

import hr.algebra.bookify.backend.model.Book;
import hr.algebra.bookify.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book create(@RequestBody Book newBook) {
        return bookService.create(newBook);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public Book update(@RequestBody Book updatedBook) {
        return bookService.update(updatedBook);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
