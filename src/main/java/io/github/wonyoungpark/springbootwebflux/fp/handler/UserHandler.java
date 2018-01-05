package io.github.wonyoungpark.springbootwebflux.fp.handler;

import io.github.wonyoungpark.springbootwebflux.domain.User;
import io.github.wonyoungpark.springbootwebflux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
@EnableAsync
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

        public Mono<ServerResponse> root(ServerRequest request) {
        return ServerResponse.ok().syncBody("오키도키").doOnSuccess(
                (str) -> log.info("root"));
    }

    public Mono<ServerResponse> blocking(ServerRequest request) {
        return ServerResponse.ok().body(Flux.fromIterable(userRepository.findByIdBefore(1000)), User.class).log();
    }

    public Mono<ServerResponse> nonBlocking(ServerRequest request) {
//        return ServerResponse.ok().body(Flux.defer(() -> Flux.fromIterable(userRepository.findAll()).take(1))
        return ServerResponse.ok().body(Flux.defer(() -> Flux.fromIterable(userRepository.findByIdBefore(1000)))
                .subscribeOn(Schedulers.elastic())
                .doOnComplete(() -> log.info("nonBlocking"))
                , User.class)
                ;
    }

}