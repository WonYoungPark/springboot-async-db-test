package io.github.wonyoungpark.springbootwebflux;

import io.github.wonyoungpark.springbootwebflux.domain.User;
import io.github.wonyoungpark.springbootwebflux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableAsync
public class SpringbootWebfluxApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		System.setProperty("reactor.ipc.netty.workerCount", "2");
		System.setProperty("reactor.ipc.netty.pool.maxConnections", "2000");

		SpringApplication.run(SpringbootWebfluxApplication.class, args);
	}

	//@Bean
	public CommandLineRunner demoData() {
		return (str) -> {
			List<User> users = new ArrayList<>();
			for (int index = 1; index <= 1000; index++) {
				User user = new User();
				user.setName("Name - " + index);
				users.add(user);
			}

			userRepository.saveAll(users);
		};
	}
}
