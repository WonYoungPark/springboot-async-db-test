package io.github.wonyoungpark.springbootwebflux.repository;

import io.github.wonyoungpark.springbootwebflux.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends CrudRepository<User, Long> {
}
