package io.github.wonyoungpark.springbootwebflux.util;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Supplier;

@Component
public class AsyncTransactionTemplate {
    private final TransactionTemplate transactionTemplate;

    public AsyncTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public <T> Mono<T> asyncTx(Supplier<T> s) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> s.get()))
                .publishOn(Schedulers.elastic())
                .publishOn(Schedulers.parallel());
    }

}
