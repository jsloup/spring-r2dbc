package jsloup.r2dbc.controller;

import jsloup.r2dbc.model.Person;
import jsloup.r2dbc.repository.ReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{key}")
    public Mono<Person> getById(@PathVariable("key") final String key) {
        return reactiveRepository.findById(key);
    }

    @PostMapping("")
    public Mono<Person> save(@RequestBody final Person person) {
        return reactiveRepository.save(person);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable final String key) {
        reactiveRepository.deleteById(key);
    }
}
