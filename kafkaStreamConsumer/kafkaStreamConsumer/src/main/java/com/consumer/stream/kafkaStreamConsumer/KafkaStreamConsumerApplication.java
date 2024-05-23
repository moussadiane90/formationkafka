package com.consumer.stream.kafkaStreamConsumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaStreamConsumerApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaStreamConsumerApplication.class, args);

		Properties properties= new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		//properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		//properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG,"formation");
		//properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,3000);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
            kafkaConsumer.subscribe(Collections.singleton("kafka-formation"));

			Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
				System.out.println("-----------------------");
				ConsumerRecords<String, String> consumerRecords=kafkaConsumer.poll(Duration.ofMillis(100));
				consumerRecords.forEach(cr->{
					System.out.println("key=>"+cr.key()+"=>"+cr.value()+"=>"+cr.offset());
				});

			},1000,1000, TimeUnit.MILLISECONDS);

    }

}
