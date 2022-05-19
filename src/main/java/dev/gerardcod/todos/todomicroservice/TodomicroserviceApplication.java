package dev.gerardcod.todos.todomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "dev.gerardcod.todos")
@SpringBootApplication
public class TodomicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodomicroserviceApplication.class, args);
	}

}
