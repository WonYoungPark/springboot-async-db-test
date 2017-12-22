package io.github.wonyoungpark.springbootwebflux.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;
import java.util.stream.Stream;

@Slf4j
@RestController
public class ExampleController {

    @GetMapping("/")
    public Mono<String> example() {
        log.info("1");
        Mono<String> mono = Mono.just(gennerateHello()).log();
        //Mono mono = Mono.fromSupplier(() -> gennerateHello()).log();
        String m = mono.block();
        log.info("2");
        return Mono.just(m);
    }

//    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Event> event() {
//        return Flux.just(new Event(1L, "1"), new Event(2L, "2"));
//    }

    // 하나의 어레이로 만드는 것이 아닌 스트림으로 만듬.!
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@GetMapping(value = "/stream")
    public Flux<Event> event() {
        return Flux
                .<Event, Long>generate(() -> 1L, (id, sink) -> {
                    sink.next(new Event(id, "value" + id));
                    return id + 1;
                })
                .delayElements(Duration.ofSeconds(1))
                .take(10);

//        return Flux
//                .<Event>generate(sink -> sink.next(new Event(System.currentTimeMillis(), "value")))
//                .delayElements(Duration.ofSeconds(1))
//                .take(10);

//        return Flux.fromStream(Stream.generate(() -> new Event(System.currentTimeMillis(), "value")))
//                .delayElements(Duration.ofSeconds(1))
//                .take(10);

        //return Flux.fromStream(Stream.generate(() -> new Event(System.currentTimeMillis(), "value")).limit(100));
    }


    private String gennerateHello() {
        log.info("method generateHello()");
        return "Hello Mono";
    }

    @Data
    @AllArgsConstructor
    public static class Event {
        private long id;
        private String value;
    }
}
