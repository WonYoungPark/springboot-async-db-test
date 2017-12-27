package io.github.wonyoungpark.springbootwebflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LoadTest {
    private final static int MAX_COUNT = 1;

    private static int port = 8080;

    private static WebClient webClient = WebClient.builder().baseUrl("http://localhost:" + port).build();

    private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_COUNT);

    public static void main(String[] args) throws InterruptedException {
        부하_테스트_블록킹();
//        부하_테스트_넌블록킹();
//        단일_테스트();
    }

    public static void 부하_테스트_블록킹() throws InterruptedException {
        요청("/test", MAX_COUNT);
    }

    public static void 부하_테스트_넌블록킹() throws InterruptedException {
        요청("/test/nonBlocking", MAX_COUNT);
    }

    public static void 단일_테스트() throws InterruptedException {
        요청("/test", 1);
    }

    private static void 요청(String uri, int count) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("테스트 시작");

        for (int i = 1; i <= count; i++) {
            executorService.execute(() -> {
                webClient.get()
                        .uri(uri)
                        .exchange()
                        .block();
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);

        stopWatch.stop();
        log.info("테스트 종료 - 소요시간 : " + stopWatch.getTotalTimeSeconds());
    }
}
