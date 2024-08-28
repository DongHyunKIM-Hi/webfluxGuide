package org.example.webfluxguide.contoller;

import lombok.RequiredArgsConstructor;
import org.example.webfluxguide.model.request.UserRequestDto;
import org.example.webfluxguide.model.response.UserResponseDto;
import org.example.webfluxguide.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public Mono<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto.getName())
            .map(UserResponseDto::of);
    }

    @GetMapping("/users/{userId}")
    public Mono<UserResponseDto> getUserById(@PathVariable Long userId) {
        return userService.getUserByUserId(userId)
            .map(UserResponseDto::of);
    }

    @GetMapping("/users")
    public Flux<UserResponseDto> getUserList() {
        return userService.getUserList()
            .map(UserResponseDto::of);
    }

    @DeleteMapping("/users/{userId}")
    public Mono<String> deletedUser(@PathVariable Long userId) {
        return userService.deleteUserByUserId(userId);
    }

    @PutMapping("/users/{userId}")
    public Mono<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(userId, requestDto.getName())
            .map(UserResponseDto::of);
    }
}
