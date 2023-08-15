package com.pdh.jwtpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class JwtPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtPracticeApplication.class, args);
	}

}
