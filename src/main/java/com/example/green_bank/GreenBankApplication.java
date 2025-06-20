package com.example.green_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GreenBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenBankApplication.class, args);
	}

}
