package com.producer.stream.kafkaStreamProducer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaStreamProducerApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaStreamProducerApplication.class, args);


		Properties properties= new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		properties.put(ProducerConfig.CLIENT_ID_CONFIG,"formation");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		Random random= new Random();
		KafkaProducer<String, String> kafkaProducer=new KafkaProducer<>(properties);
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
			String key=String.valueOf(random.nextInt(1000));
			String msg=String.valueOf(random.nextDouble()*99999);
			kafkaProducer.send(new ProducerRecord<String, String>("kafka-formation",key, msg),
					(metadata,ex)->{
				    System.out.println("sending Message key=>"+key+"value=>"+msg);
				System.out.println("Partition=>"+metadata.partition()+" offset=>"+metadata.offset());
			       });
		            },1000,1000, TimeUnit.MILLISECONDS);
	}
}
