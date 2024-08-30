package org.example.webfluxguide.contoller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.example.webfluxguide.model.request.UserRequestDto;
import org.example.webfluxguide.model.response.UserResponseDto;
import org.example.webfluxguide.repository.User;
import org.example.webfluxguide.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(UserController.class)
@AutoConfigureWebTestClient
class UserControllerTest {

    @Autowired
    private WebTestClient client;
    @MockBean
    private UserService userService;


    @Test
    void createUser() {

        when(userService.createUser("Viva")).thenReturn(
            Mono.just(new User(1L,"Viva", LocalDateTime.now(),LocalDateTime.now()))
        );

        client.post().uri("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UserRequestDto("Viva"))
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponseDto.class)
            .value(response -> {
                assertEquals("Viva", response.getName());
            });

    }

    @Test
    void getUserById() {

        when(userService.getUserByUserId(1L)).thenReturn(
            Mono.just(new User(1L,"Viva", LocalDateTime.now(),LocalDateTime.now()))
        );

        client.get().uri("/users/1")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponseDto.class)
            .value(res ->
                assertEquals("Viva",res.getName())
            );
    }

    @Test
    void getUserById_Not_Found() {

        when(userService.getUserByUserId(1L)).thenReturn(
            Mono.empty()
        );

        client.get().uri("/users/1")
            .exchange()
            .expectStatus().is4xxClientError();
    }

    @Test
    void getUserList() {

        when(userService.getUserList()).thenReturn(
            Flux.just(
                new User(1L,"Viva", LocalDateTime.now(),LocalDateTime.now()),
                new User(2L,"Viva", LocalDateTime.now(),LocalDateTime.now()),
                new User(3L,"Viva", LocalDateTime.now(),LocalDateTime.now()),
                new User(4L,"Viva", LocalDateTime.now(),LocalDateTime.now())
            )
        );

        client.get().uri("/users")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBodyList(UserResponseDto.class)
            .hasSize(4);
    }

    @Test
    void deletedUser() {

        when(userService.deleteUserByUserId(1L)).thenReturn(
            Mono.just("삭제할게 없음")
        );

        client.delete().uri("/users/1")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(String.class)
            .value(res ->
                assertEquals("삭제할게 없음", res)
                )
        ;
    }

    @Test
    void updateUser() {

        when(userService.updateUser(1L,"Aviv")).thenReturn(
            Mono.just(new User(1L,"Aviv", LocalDateTime.now(),LocalDateTime.now()))
        );

        client.put().uri("/users/1")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UserRequestDto("Aviv"))
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(UserResponseDto.class)
            .value(response -> {
                assertEquals("Aviv", response.getName());
            });

    }
}