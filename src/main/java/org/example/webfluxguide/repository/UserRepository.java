package org.example.webfluxguide.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);
    Flux<User> findAll();
    Mono<User> findById(long id);
    Mono<String> deleteById(long id);
}
