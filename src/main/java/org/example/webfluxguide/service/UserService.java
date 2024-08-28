package org.example.webfluxguide.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.example.webfluxguide.repository.User;
import org.example.webfluxguide.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> createUser(String name) {
        return userRepository.save(User.builder().name(name).build());
    }

    public Flux<User> getUserList() {
        return userRepository.findAll();
    }

    public Mono<User> getUserByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    public Mono<String> deleteUserByUserId(Long userId) {
        return userRepository.deleteById(userId);
    }

    public Mono<User> updateUser(long userId, String name) {
        return userRepository.findById(userId)
            .flatMap(u -> {
                u.setName(name);
                u.setUpdated(LocalDateTime.now());
                return userRepository.save(u);
            });
    }
}
