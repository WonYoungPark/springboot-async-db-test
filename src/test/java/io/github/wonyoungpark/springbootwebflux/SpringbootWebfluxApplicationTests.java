package io.github.wonyoungpark.springbootwebflux;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

//@RunWith(SpringRunner.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SpringbootWebfluxApplicationTests {
    private final static int MAX_COUNT = 20;

    private int port = 8080;

    private WebClient webClient;

    @Autowired
	private WebTestClient webTestClient;

	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_COUNT);

	@Before
    public void setUp() {
	    webClient = WebClient.builder().baseUrl("http://localhost:" + port).build();
    }

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
	public void 부하_테스트_블록킹() throws InterruptedException {
        요청("/test", MAX_COUNT);
    }

    @Test
	public void 부하_테스트_넌블록킹() throws InterruptedException {
        요청("/test/nonBlocking", MAX_COUNT);
    }

    @Test
    public void 단일_테스트() throws InterruptedException {
        요청("/test", 1);
    }

    private void 요청(String uri, int count) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("테스트 시작");

        for (int i = 1; i <= count; i++) {
            executorService.execute(() -> {
                webClient.get()
                        .uri(uri)
                        .exchange()
                        .log()
                        ;
//                webTestClient.get()
//                        .uri(uri)
//                        .exchange()
//                        .expectStatus().isOk()
//                        .expectBody()
//                        .consumeWith((str) -> {
//                            //log.info(str.toString());
//                        })
//                        ;
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);

        stopWatch.stop();
        log.info("테스트 종료 - 소요시간 : " + stopWatch.getTotalTimeSeconds());
    }
}
