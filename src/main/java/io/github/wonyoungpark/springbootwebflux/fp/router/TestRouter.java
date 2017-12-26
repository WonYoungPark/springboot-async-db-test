package io.github.wonyoungpark.springbootwebflux.fp.router;

import io.github.wonyoungpark.springbootwebflux.fp.handler.TestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Slf4j
@Component
public class TestRouter {
    @Autowired
    private TestHandler testHandler;

    @Bean
    public RouterFunction<?> testRouterFunction() {
        return RouterFunctions.route(GET("/test"), testHandler::blocking)
                           .andRoute(GET("/test/nonBlocking"), testHandler::nonBlocking);
                           //.andRoute(GET("/test2"), request -> ServerResponse.ok().body(testHandler.nonBlocking(), User.class));
    }
}
