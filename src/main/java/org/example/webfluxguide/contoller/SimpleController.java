package org.example.webfluxguide.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SimpleController {

    @GetMapping("/webflux/simple")
    public Mono<String> getHelloWorld() {
        return Mono.just("Hello World!");  
    }
}
