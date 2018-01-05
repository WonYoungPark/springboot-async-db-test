//package io.github.wonyoungpark.springbootwebflux.fp.handler;
//
//import io.github.wonyoungpark.springbootwebflux.domain.Test;
//import io.github.wonyoungpark.springbootwebflux.repository.TestRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.core.scheduler.Schedulers;
//
//@Slf4j
//@Component
//@EnableAsync
//public class TestHandler {
//    @Autowired
//    private TestRepository testRepository;
//
//    public Mono<ServerResponse> root(ServerRequest request) {
//        return ServerResponse.ok().syncBody("오키도키");
//    }
//
//    public Mono<ServerResponse> blocking(ServerRequest request) {
//        return ServerResponse.ok()
//                .body(Flux.fromIterable(testRepository.findAll()), Test.class)
//                .log();
//    }
//
//    // 블록킹 저장소로부터 모든 User를 읽기 위해 subscrive될 때까지 조회를 지연(defer)하는 Flux를 생성하고 이것을 elastic Scheduler로 실행
//    public Mono<ServerResponse> nonBlocking(ServerRequest request) {
//        return ServerResponse.ok()
//                .body(Flux.defer(() -> {
//                    try {
//                        log.info("동작 고고고고고싱");
//                        Thread.sleep(1000L);
//                    } catch (Exception e) {
//
//                    }
//                    log.info("동작 끝!!!!!!!!!!!!!!!!");
//                    return Flux.fromIterable(testRepository.findAll());
//                })
//                    .subscribeOn(Schedulers.elastic()), Test.class)
//                ;
//    }
//}
