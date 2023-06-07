package hr.algebra.bookify.backend.service;

import hr.algebra.bookify.backend.model.Book;
import hr.algebra.bookify.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book create(Book newBook) {
        return bookRepository.save(newBook);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public Book update(Book updatedBook) {
        return bookRepository.save(updatedBook);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
