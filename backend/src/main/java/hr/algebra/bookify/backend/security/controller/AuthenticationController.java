package hr.algebra.bookify.backend.security.controller;

import hr.algebra.bookify.backend.security.model.JWT;
import hr.algebra.bookify.backend.security.model.User;
import hr.algebra.bookify.backend.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("login")
    public JWT login(@RequestBody final User user) {
        return authenticationService.login(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials"));
    }

}
