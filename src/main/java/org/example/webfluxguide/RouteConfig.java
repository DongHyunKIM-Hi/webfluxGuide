package org.example.webfluxguide;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final SimpleHandler simpleHandler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
            .GET("/test", simpleHandler::getString)
            .build();
    }
}
