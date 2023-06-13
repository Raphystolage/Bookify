package hr.algebra.bookify.webapp;

import com.opencsv.CSVWriter;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    Logger logger = LoggerFactory.getLogger(BookController.class);


    @GetMapping
    public String getAll(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        logger.info("Total price: " + bookService.calculateTotalPrice(books).toString());
        return "book-list";
    }

    @PostMapping("getById")
    public String postGetById(@Nullable @RequestParam("id") String id) {
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
        model.addAttribute("redirectionUrl","/book/new");
        return "book-form";
    }

    @Timed(value = "create.time", description = "Execution time of creation")
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
        model.addAttribute("redirectionUrl","/book/edit/"+id);
        return "book-form";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("book") Book book) {
        book.setId(id);
        bookService.update(book);
        return "redirect:/book";
    }

    @Timed(value = "delete.time", description = "Execution time of deletion")
    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @Timed(value = "export.time", description = "Execution time of export method")
    @GetMapping("export")
    public String export() {

        String fileName = "output.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(new File("out",fileName)))) {
            String[] header = {"ID", "Title", "Author", "Type", "Release Date", "Price"};
            writer.writeNext(header);

            for (Book book : bookService.getAll()) {
                String[] data = {
                    String.valueOf(book.getId()),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getType().toString(),
                    book.getReleaseDate().toString(),
                    String.valueOf(book.getPrice())
                };
                writer.writeNext(data);
            }

            logger.info("Export successful. File saved at: " + fileName);
        } catch (IOException e) {
            logger.error("Export failed. Error: " + e.getMessage());
        }
        return "redirect:/book";
    }

}
