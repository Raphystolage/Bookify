package hr.algebra.bookify.backend.controller;

import hr.algebra.bookify.backend.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public abstract class AbstractController<T> {

    protected AbstractService<T> service;

    @Autowired
    protected JmsTemplate JMS_TEMPLATE;

    public AbstractController(AbstractService<T> service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public T getById(@PathVariable final Long id) {
        JMS_TEMPLATE.convertAndSend("Get objects by id " + id);
        return service.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<T> getAll() {
        JMS_TEMPLATE.convertAndSend("Get all objects");
        return service.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public T create(@RequestBody T newObject) {
        JMS_TEMPLATE.convertAndSend("Create " + newObject.getClass().getSimpleName());
        return service.create(newObject);
    }

    @PutMapping
    public T update(@RequestBody T updatedObject) {
        JMS_TEMPLATE.convertAndSend("Update " + updatedObject.getClass().getSimpleName());
        return service.update(updatedObject);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        JMS_TEMPLATE.convertAndSend("Delete object by id " + id);
        service.deleteById(id);
    }

}
