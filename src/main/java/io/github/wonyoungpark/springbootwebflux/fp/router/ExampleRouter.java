package io.github.wonyoungpark.springbootwebflux.fp.router;

import io.github.wonyoungpark.springbootwebflux.fp.handler.ExampleHandler;
import io.github.wonyoungpark.springbootwebflux.fp.handler.HelloHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class ExampleRouter {
    @Autowired
    private ExampleHandler exampleHandler;

    @Bean
    public RouterFunction<?> ExampleRouterFuntion() {
        return route(GET("/example"), exampleHandler::getAll)
                .andRoute(POST("/example"), exampleHandler::save);
    }
}

