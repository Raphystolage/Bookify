package hr.algebra.bookify.backend.controller;

import hr.algebra.bookify.backend.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public abstract class AbstractController<T> {

    protected AbstractService<T> service;

    public AbstractController(AbstractService<T> service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("{id}")
    public T getById(@PathVariable final Long id) {
        return service.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<T> getAll() {
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public T create(@RequestBody T newStorable) {
        return service.create(newStorable);
    }

    @PutMapping
    public T update(@RequestBody T updatedClassicDBStorable) {
        return service.update(updatedClassicDBStorable);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
