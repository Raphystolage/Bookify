package hr.algebra.bookify.webapp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final String URL = "http://10.42.0.1:8080/book";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public Book create(Book newBook) {
        HttpEntity<Book> request = new HttpEntity<>(newBook);
        ResponseEntity<Book> response = REST_TEMPLATE.exchange(URL, HttpMethod.POST, request, Book.class);
        return response.getBody();
    }

    public Book getById(Long id) {
        return REST_TEMPLATE.getForObject(URL+"/"+id, Book.class);
    }

    public List<Book> getAll() {
        ResponseEntity<Book[]> response = REST_TEMPLATE.getForEntity(URL, Book[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }

    public void update(Book updatedBook) {
        HttpEntity<Book> request = new HttpEntity<>(updatedBook);
        ResponseEntity<Book> response = REST_TEMPLATE.exchange(URL, HttpMethod.PUT, request, Book.class);
    }

    public void deleteById(Long id) {
        REST_TEMPLATE.delete(URL+"/"+id);
    }

    public List<Book> filterBooksByAuthor(List<Book> books, String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public Double calculateTotalPrice(List<Book> books) {
        return books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
    }

    public List<Book> sortBooksByReleaseDate(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getReleaseDate))
                .collect(Collectors.toList());
    }

}
