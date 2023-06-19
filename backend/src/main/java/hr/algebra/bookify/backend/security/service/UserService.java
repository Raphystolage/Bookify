package hr.algebra.bookify.backend.security.service;

import hr.algebra.bookify.backend.security.model.User;
import hr.algebra.bookify.backend.security.repository.UserRepository;
import hr.algebra.bookify.backend.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> getByUsername(String username) {
        return ((UserRepository) repository).findByUsername(username);
    }

}
