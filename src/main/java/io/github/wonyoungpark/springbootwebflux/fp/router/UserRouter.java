package io.github.wonyoungpark.springbootwebflux.fp.router;

import io.github.wonyoungpark.springbootwebflux.fp.handler.UserHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Component
public class UserRouter {
    @Autowired
    private UserHandler userHandler;

    private final String URI = "/user";

    @Bean
    public RouterFunction<?> userRouterFunction() {
        return nest(path(URI),
                    nest(accept(MediaType.APPLICATION_JSON_UTF8),
                        route(GET("/blocking").and(accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::blocking)
                        .andRoute(GET("/non-blocking").and(accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::nonBlocking)
                                .andRoute(GET("").and(accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::root)
                )
        );
    }
}
