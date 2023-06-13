package hr.algebra.bookify.backend.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractService<T> {

    protected JpaRepository<T, Long> repository;

    public AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T create(T newStorable) {
        return repository.save(newStorable);
    }

    public Optional<T> getById(Long id) {
        return repository.findById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T update(T updatedClassicDBStorable) {
        return repository.save(updatedClassicDBStorable);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
