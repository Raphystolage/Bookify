package hr.algebra.bookify.webapp.controller;

import hr.algebra.bookify.webapp.model.JWT;
import hr.algebra.bookify.webapp.model.User;
import hr.algebra.bookify.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String postLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User(username, password);
        JWT jwt = userService.send(user);
        System.out.println(jwt);
        return "redirect:/book";
    }

}
