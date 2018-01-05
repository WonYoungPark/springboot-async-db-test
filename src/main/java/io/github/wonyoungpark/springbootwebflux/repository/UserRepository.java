package io.github.wonyoungpark.springbootwebflux.repository;

import io.github.wonyoungpark.springbootwebflux.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Iterable<User> findByIdBefore(Integer id);
}
