package com.spring.kafka.kafkaSpringProducer;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Employee {
    private String name;
    private String departement;
    private double salary;

}
