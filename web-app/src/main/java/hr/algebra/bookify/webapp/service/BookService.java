package hr.algebra.bookify.webapp.service;

import hr.algebra.bookify.webapp.model.Book;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    public Book create(Book newBook, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Book> request = new HttpEntity<>(newBook, headers);
        ResponseEntity<Book> response = REST_TEMPLATE.exchange(URL, HttpMethod.POST, request, Book.class);
        return response.getBody();
    }

    public Book getById(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Book> response = REST_TEMPLATE.exchange(URL + "/" + id, HttpMethod.GET, request, Book.class);
        return response.getBody();
    }

    public List<Book> getAll(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Book[]> response = REST_TEMPLATE.exchange(URL, HttpMethod.GET, request, Book[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }

    public void update(Book updatedBook, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Book> request = new HttpEntity<>(updatedBook, headers);
        REST_TEMPLATE.exchange(URL, HttpMethod.PUT, request, Book.class);
    }

    public void deleteById(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> request = new HttpEntity<>(headers);
        REST_TEMPLATE.exchange(URL + "/" + id, HttpMethod.DELETE, request, String.class);
    }

}
