package com.formation.kafka.kafkaconsumer;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class ConsumerService {

    @Value(value="${kafka.bootstrap-server}")
    private String bootStrapServer;

    @Value(value="${kafka.group-id}")
    private String groupId;

    @Value(value="${kafka.topic}")
    private String topic;

    @Value(value="${kafka.offset}")
    private String offSet;

    private Properties properties;
    private Consumer<String, String> consumer;

    @PostConstruct
    public void setUp(){
        Properties properties=new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,offSet);

        consumer=new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }
    @Scheduled(fixedRate = 5000)
    public void process(){
        ConsumerRecords records=consumer.poll(Duration.ofMillis(100));
        records.forEach(stringStringConsumerRecord->System.out.println(stringStringConsumerRecord));
    }
}
