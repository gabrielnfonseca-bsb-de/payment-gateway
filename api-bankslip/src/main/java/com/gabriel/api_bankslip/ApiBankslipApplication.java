package com.gabriel.api_bankslip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ApiBankslipApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBankslipApplication.class, args);
	}

}
