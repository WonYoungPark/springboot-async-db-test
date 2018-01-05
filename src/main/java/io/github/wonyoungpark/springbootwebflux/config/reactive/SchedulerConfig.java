//package io.github.wonyoungpark.springbootwebflux.config.reactive;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.scheduler.Scheduler;
//import reactor.core.scheduler.Schedulers;
//
//import java.util.concurrent.Executors;
//
//@Slf4j
//@Configuration
//public class SchedulerConfig {
//    private final int connectionPoolSize;
//
////    @Autowired
////    public SchedulerConfig(@Value("${spring.datasource.dbcp2.max-total}") int connectionPoolSize) {
////        this.connectionPoolSize = connectionPoolSize;
////        log.info(this.connectionPoolSize + "");
////    }
//    @Autowired
//    public SchedulerConfig(@Value(value = "1") int connectionPoolSize) {
//        this.connectionPoolSize = connectionPoolSize;
//        log.info(this.connectionPoolSize + "");
//    }
//
//    @Bean
//    public Scheduler jdbcScheduler() {
//        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
//    }
//}
