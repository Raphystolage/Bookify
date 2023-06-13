package hr.algebra.bookify.backend.service;

import hr.algebra.bookify.backend.model.Book;
import hr.algebra.bookify.backend.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<Book> {
    public BookService(BookRepository repository) {
        super(repository);
    }
}
