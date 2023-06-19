package hr.algebra.bookify.webapp.service;

import hr.algebra.bookify.webapp.model.JWT;
import hr.algebra.bookify.webapp.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private static final String URL = "http://localhost:8080/authentication/login";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public JWT send(User user) {
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<JWT> response = REST_TEMPLATE.exchange(URL, HttpMethod.POST, request, JWT.class);
        return response.getBody();
    }

}
