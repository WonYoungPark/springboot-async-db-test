//package io.github.wonyoungpark.springbootwebflux.fp.router;
//
//import io.github.wonyoungpark.springbootwebflux.fp.handler.TestHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.RouterFunction;
//
//import static org.springframework.web.reactive.function.server.RequestPredicates.*;
//import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//
//@Slf4j
//@Component
//public class TestRouter {
//    @Autowired
//    private TestHandler testHandler;
//
//    private final String URI = "/test";
//
//    @Bean
//    public RouterFunction<?> testRouterFunction() {
//        return nest(path(URI),
//                    nest(accept(MediaType.APPLICATION_JSON_UTF8),
//                        route(GET("/blocking").and(accept(MediaType.APPLICATION_JSON_UTF8)), testHandler::blocking)
//                        .andRoute(GET("/non-blocking").and(accept(MediaType.APPLICATION_JSON_UTF8)), testHandler::nonBlocking)
//                        .andRoute(GET("").and(accept(MediaType.APPLICATION_JSON_UTF8)), testHandler::root)
//                )
//        );
//    }
//}
