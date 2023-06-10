package hr.algebra.bookify.webapp;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "book-list";
    }

    @PostMapping("getById")
    public String getById(@Nullable @RequestParam("id") String id) {
        try {
            Long.valueOf(id);
        } catch(NumberFormatException nfe) {

            return "redirect:/book";
        }
        return "redirect:/book/" + id;
    }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getById(id);
        if(book==null) {
            return "redirect:/book";
        } else {
            List<Book> books = new ArrayList<>();
            books.add(book);
            model.addAttribute("books", books);
            return "book-list";
        }
    }

    @GetMapping("new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("new")
    public String create(@ModelAttribute("book") Book book, @RequestParam("bookType") String bookType) {
        book.setType(BookType.valueOf(bookType));
        bookService.create(book);
        return "redirect:/book";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        book.setId(id);
        bookService.update(book);
        return "redirect:/book";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

}
