package hr.algebra.bookify.webapp.controller;

import hr.algebra.bookify.webapp.model.JWT;
import hr.algebra.bookify.webapp.model.User;
import hr.algebra.bookify.webapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
    public String postLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        User user = new User(username, password);
        try {
            JWT jwt = userService.send(user);
            session.setAttribute("token",jwt);
        } catch(HttpClientErrorException e) {
            return "redirect:/";
        }
        return "redirect:/book";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
