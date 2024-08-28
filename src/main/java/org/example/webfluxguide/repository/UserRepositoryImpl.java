package org.example.webfluxguide.repository;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private static AtomicLong seq = new AtomicLong(1L);

    @Override
    public Mono<User> save(User user) {
        var now = LocalDateTime.now();
        if(user.getId() == null){
            user.setId(seq.getAndAdd(1L));
            user.setName(user.getName());
            user.setCreated(now);
        }

        user.setUpdated(now);

        users.put(user.getId(), user);
        return Mono.just(user);
    }

    @Override
    public Flux<User> findAll() {
        return Flux.fromIterable(users.values());
    }

    @Override
    public Mono<User> findById(long id) {
        return Mono.justOrEmpty(users.getOrDefault(id,null));
    }

    @Override
    public Mono<String> deleteById(long id) {

        User user = users.getOrDefault(id, null);
        if (user == null) {
            return Mono.just("삭제할게 없음");
        } else {
            users.remove(id,user);
            return Mono.just(user.toString());
        }
    }


}
