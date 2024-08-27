package org.example.webfluxguide;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SimpleHandler {

    public Mono<ServerResponse> getString(ServerRequest request) {
        return ServerResponse.ok().bodyValue("이거이거 webflux 가이드 시작이네");
    }
}
