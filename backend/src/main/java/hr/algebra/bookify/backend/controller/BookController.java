package hr.algebra.bookify.backend.controller;

import hr.algebra.bookify.backend.model.Book;
import hr.algebra.bookify.backend.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController extends AbstractController<Book> {

    public BookController(BookService service) {
        super(service);
    }

}