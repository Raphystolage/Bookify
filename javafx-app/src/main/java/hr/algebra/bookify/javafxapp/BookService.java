package hr.algebra.bookify.javafxapp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class BookService {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public Book create(Book newBook) {
        HttpEntity<Book> request = new HttpEntity<>(newBook);
        ResponseEntity<Book> response = REST_TEMPLATE.exchange("http://localhost:8081/", HttpMethod.POST, request, Book.class);
        return response.getBody();
    }

    public Book getById(Long id) {
        return REST_TEMPLATE.getForObject("http://localhost:8082/?id="+id, Book.class);
    }

    public List<Book> getAll() {
        ResponseEntity<Book[]> response = REST_TEMPLATE.getForEntity("http://localhost:8080/book", Book[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }

    public void update(Book updatedBook) {
        HttpEntity<Book> request = new HttpEntity<>(updatedBook);
        ResponseEntity<Book> response = REST_TEMPLATE.exchange("http://localhost:8083/", HttpMethod.PUT, request, Book.class);
    }

    public void deleteById(Long id) {
        REST_TEMPLATE.delete("http://localhost:8084/?id="+id);
    }

}
