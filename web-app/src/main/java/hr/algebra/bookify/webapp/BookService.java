package hr.algebra.bookify.webapp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private static final String URL = "http://localhost:8080/book";
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

}
