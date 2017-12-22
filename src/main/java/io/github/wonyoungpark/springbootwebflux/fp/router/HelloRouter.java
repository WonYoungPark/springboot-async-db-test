package io.github.wonyoungpark.springbootwebflux.fp.router;

import io.github.wonyoungpark.springbootwebflux.fp.handler.ExampleHandler;
import io.github.wonyoungpark.springbootwebflux.fp.handler.HelloHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class HelloRouter {

    @Autowired
    private HelloHandler helloHandler;

    //@Bean
    public RouterFunction<?> helloRouterFunction() {
        return route(GET("/test"), helloHandler::test)
                .andOther(route(RequestPredicates.all(), helloHandler::error));
    }

    //@Bean
    public RouterFunction<?> helloRouterFunction2() {
        return route(GET("/users"), helloHandler::test2)
                .andRoute(GET("/3"), helloHandler::test2);
                //.andOther(route(RequestPredicates.all(), helloHandler::error));
    }
//
//    @ResponseStatus(value= HttpStatus.NOT_FOUND)
//    public Mono<ServerResponse> errorHandler(WebExceptionHandler e) throws Exception {
//        return ServerResponse.ok().body(fromObject("에러2"));
//        //return Mono.just(fromObject("에러"));
//    }
}

