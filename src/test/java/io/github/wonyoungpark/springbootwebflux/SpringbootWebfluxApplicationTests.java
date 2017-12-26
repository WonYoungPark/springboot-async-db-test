package io.github.wonyoungpark.springbootwebflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SpringbootWebfluxApplicationTests {
    private final static int MAX_COUNT = 20;

	@Autowired
	private WebTestClient webTestClient;

	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_COUNT);

//	@Test
//	public void contextLoads() {
//        executorService = Executors.newFixedThreadPool(MAX_COUNT);
//        for (int i = 0; i < MAX_COUNT; i++) {
//            executorService.execute(() -> {
//                log.info("쓰레드 생성 - {}", Thread.currentThread().getName());
//            });
//
//        }
//    }

	@Test
	public void 부하_테스트() throws InterruptedException {
        요청(MAX_COUNT);
    }

    @Test
    public void 단일_테스트() throws InterruptedException {
        요청(1);
    }

    private void 요청(int count) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("테스트 시작");

        for (int i = 1; i <= count; i++) {
            executorService.execute(() -> {
                webTestClient.get()
                        .uri("/test")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody()
                        .consumeWith((str) -> {
                            //log.info(str.toString());
                        });
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);

        stopWatch.stop();
        log.info("테스트 종료 - 소요시간 : " + stopWatch.getTotalTimeSeconds());
    }
}
