package br.com.validator.payment_validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PaymentValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentValidatorApplication.class, args);
	}

}
