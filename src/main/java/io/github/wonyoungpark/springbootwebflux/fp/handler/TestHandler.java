package io.github.wonyoungpark.springbootwebflux.fp.handler;

import io.github.wonyoungpark.springbootwebflux.domain.User;
import io.github.wonyoungpark.springbootwebflux.repository.BlockingRepository;
import io.github.wonyoungpark.springbootwebflux.repository.NonBlockingRepository;
import io.github.wonyoungpark.springbootwebflux.util.AsyncTransactionTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static org.springframework.web.reactive.function.server.EntityResponse.fromObject;

@Slf4j
@Component
@EnableAsync
public class TestHandler {
    @Autowired
    private BlockingRepository blockingRepository;

    @Autowired
    private NonBlockingRepository nonBlockingRepository;

//    @Autowired
//    private Scheduler scheduler;

//    @Autowired
//    private AsyncTransactionTemplate asyncTransactionTemplate;

//    @Autowired
//    private NonBlockingRepository nonBlockingRepository;


//    public Mono<ServerResponse> nonBlocking(ServerRequest request) {
//        return async(() -> nonBlockingRepository.findAll());
//        //return ServerResponse.ok().syncBody(fromObject("nonBlocking"));
//    }

//    public Flux<User> nonBlocking() {
//        return asyncTransactionTemplate.asyncTx(() -> nonBlockingRepository.findAll()).flatMapIterable(v -> v);
//    }

    public Mono<ServerResponse> blocking(ServerRequest request) {
        try {
            //log.info(Thread.currentThread().getName());
            Thread.sleep(1000L);
        } catch (Exception e) {

        }
        //return ServerResponse.ok().syncBody(blockingRepository.findAll());
        return ServerResponse.ok().syncBody(fromObject("111"));
    }

    public Mono<ServerResponse> nonBlocking(ServerRequest request) {
        return ServerResponse.ok().syncBody(fromObject("111"));
    }

//    private <T> Mono<T> async(Callable<T> callable) {
//        return Mono.fromCallable(callable).publishOn(scheduler);
//    }
}
