package hr.algebra.bookify.webapp;

import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @PostMapping("new")
    public String create(@ModelAttribute("book") Book book,
                         @RequestParam("bookType") String bookType,
                         @RequestParam("title") String title,
                         @RequestParam("author") String author) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setType(BookType.valueOf(bookType));
        if(StringSecurity.isSafe(title+author))
            bookService.create(book);
        else
            logger.error("Unauthorized character or word detected");
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
    public String update(@PathVariable("id") Long id, @ModelAttribute("book") Book book,
                         @RequestParam("bookType") String bookType,
                         @RequestParam("title") String title,
                         @RequestParam("author") String author) {
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setType(BookType.valueOf(bookType));
        if(StringSecurity.isSafe(title+author))
            bookService.update(book);
        else
            logger.error("Unauthorized characters or word detected");
        return "redirect:/book";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

    @GetMapping("export")
    public ResponseEntity<Resource> export() {
        String fileName = SerializeBookList.serializeBookList(new ArrayList<>(bookService.getAll()));

        Resource resource = new FileSystemResource(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/import")
    public String importBooks(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            logger.warn("Empty ser file");
            return "redirect:/book";
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            Path path = Paths.get(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Book> books = SerializeBookList.deserializeBookList(fileName);
        for(Book book : books) {
            book.setId(null);
            bookService.create(book);
            logger.info("Book \"" + book.getTitle() + "\" imported");
        }

        return "redirect:/book";
    }

}
