package jsloup.r2dbc.controller;

import jsloup.r2dbc.model.Person;
import jsloup.r2dbc.repository.ReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/person")
@Slf4j
public class PersonController {

    private final ReactiveRepository reactiveRepository;

    public PersonController(ReactiveRepository reactiveRepository) {
        this.reactiveRepository = reactiveRepository;
    }

    @GetMapping("")
    public Flux<Person> getAll() {
        return reactiveRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Person> getById(@PathVariable("id") final Integer id) {
        return reactiveRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public Flux<Person> getByName(@PathVariable("name") final String name) {
        return reactiveRepository.findByName(name);
    }

    @PostMapping("")
    public Mono<Person> create(@RequestBody final Person person) {
        return reactiveRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final Integer id) {
        return reactiveRepository.deleteById(id);
    }
}
