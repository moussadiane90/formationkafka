package com.spring.kafka.kafkaSpringConsumer;

import com.spring.kafka.kafkaSpringProducer.PageEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MesssageService {

    @KafkaListener(topics="kafka-formation", groupId="formation")
    public void onMessage(ConsumerRecord<String, PageEvent> message){
        System.out.println("************");

           System.out.println("key=>"+message.key());
        System.out.println(message.value().getDate()+";"+message.value().getDate()+","+message.value().getDuration());

    }
}
