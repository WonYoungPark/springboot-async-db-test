package io.github.wonyoungpark.springbootwebflux.fp.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
public class HelloHandler {
    public Mono<ServerResponse> test(ServerRequest request) {
        return ServerResponse.ok().body(fromObject("안녕"));
    }
    public Mono<ServerResponse> test2(ServerRequest request) {
        return ServerResponse.ok().body(fromObject("안녕2"));
    }

    public Mono<ServerResponse> error(ServerRequest request) {
        return ServerResponse.ok().body(fromObject("에러"));
    }

}
