package io.github.wonyoungpark.springbootwebflux.fp.handler;

import io.github.wonyoungpark.springbootwebflux.domain.User;
import io.github.wonyoungpark.springbootwebflux.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class ExampleHandler {
    @Autowired
    private ExampleRepository exampleRepository;

    public Mono<ServerResponse> getAll(ServerRequest request) {
//        Flux<User> flux = exampleRepository.findAll();
        return ServerResponse.ok().syncBody("");
        //return ServerResponse.ok().syncBody(exampleRepository.findAll());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        long tempLong = System.currentTimeMillis();
        exampleRepository.save(new User(tempLong, "안녕" + tempLong));
        return ServerResponse.ok().build();
    }
}
