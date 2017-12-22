package io.github.wonyoungpark.springbootwebflux;

import io.github.wonyoungpark.springbootwebflux.domain.User;
import io.github.wonyoungpark.springbootwebflux.repository.ExampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringbootWebfluxApplication {
	@Autowired
	private ExampleRepository exampleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData() {
		return (str) -> {
			List<User> users = new ArrayList<>();
			for (int index = 0; index < 100; index++) {
				User user = new User();
				user.setName("이름 - " + index);
				users.add(user);
			}

			exampleRepository.saveAll(users);
		};
	}
}
