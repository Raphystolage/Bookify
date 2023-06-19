package hr.algebra.bookify.backend.security.service;

import hr.algebra.bookify.backend.security.model.JWT;
import hr.algebra.bookify.backend.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    public Optional<JWT> login(User user) {
        Optional<User> realUser = userService.getByUsername(user.getUsername());

        if (realUser.isEmpty() || !isMatchingPassword(user.getPassword(), realUser.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new JWT(jwtService.createJwt(realUser.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }

}
