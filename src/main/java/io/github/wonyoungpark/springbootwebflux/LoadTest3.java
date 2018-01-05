package io.github.wonyoungpark.springbootwebflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class LoadTest3 {
    public static void main(String[] args) {
        final String HOST = "http://localhost:8080";
        final String URI = "/test/non-blocking";

        WebClient webClient = WebClient.create(HOST);
        while (true) {
            log.info("11");
//
            webClient.get()
                    .uri(URI)
                    .exchange()
                    .doOnSuccess((str) -> log.info(str.toString()))
                    .block();
//                    .then()
//                    .log();
        }
    }
}
