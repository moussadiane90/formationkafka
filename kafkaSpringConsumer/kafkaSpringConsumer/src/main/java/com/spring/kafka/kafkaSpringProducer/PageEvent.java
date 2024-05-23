package com.spring.kafka.kafkaSpringProducer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class PageEvent {
    private String page;
    private Date date;
    private int duration;
}
