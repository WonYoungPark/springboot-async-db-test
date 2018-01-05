package io.github.wonyoungpark.springbootwebflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LoadTest {
    private static final int MAX_COUNT= 100;
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        ExecutorService ex = Executors.newFixedThreadPool(MAX_COUNT);

        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8080/test/findAll";

        CyclicBarrier barrier = new CyclicBarrier(MAX_COUNT + 1);

        for (int i = 0 ; i < MAX_COUNT ; i ++) {
            ex.submit(()->{
                int idx = counter.incrementAndGet();

                barrier.await();

                log.info("Thread:{}", idx);

                StopWatch sw = new StopWatch();
                sw.start();
                String res = rt.getForObject(url, String.class, idx);
                sw.stop();
                log.info("Elapse: {}->{} / {}", idx, +sw.getTotalTimeSeconds(), res);
                return null;// for callable interface impl lambda.
            });
        }

        barrier.await();
        StopWatch main = new StopWatch();
        main.start();

        ex.shutdown();
        ex.awaitTermination(100, TimeUnit.SECONDS);

        main.stop();
        log.info("total:{}", main.getTotalTimeSeconds());

    }
}
