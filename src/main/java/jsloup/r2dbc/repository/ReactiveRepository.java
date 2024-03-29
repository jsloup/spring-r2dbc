package jsloup.r2dbc.repository;

import jsloup.r2dbc.model.Person;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveRepository extends ReactiveCrudRepository<Person, Integer> {

    @Query("SELECT * FROM person WHERE name = :name")
    Flux<Person> findByName(String name);
}
