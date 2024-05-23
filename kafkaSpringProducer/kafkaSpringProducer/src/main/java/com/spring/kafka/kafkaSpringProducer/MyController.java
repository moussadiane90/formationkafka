package com.spring.kafka.kafkaSpringProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class MyController {

     @Autowired
     private KafkaTemplate<String, PageEvent> kafkaTemplate;

     private String topic="kafka-formation";
   /*  public MyController(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
        }*/
    @GetMapping("/send/{page}")
    public String send(@PathVariable String page){

        PageEvent pageEvent=new PageEvent(page,new Date(),new Random().nextInt(1000));
    ///kafkaTemplate.send(topic, new Employee(name,"finance",4500.0));
        kafkaTemplate.send(topic,"key"+pageEvent.getPage(), pageEvent);
    return "Message sent ....";

    }
}
