package com.spring.kafka.kafkaSpringProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaSpringProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringProducerApplication.class, args);
	}

}
