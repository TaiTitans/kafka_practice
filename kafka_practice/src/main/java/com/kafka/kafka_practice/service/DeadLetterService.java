package com.kafka.kafka_practice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeadLetterService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DeadLetterService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToDLQ(String topic, String message){
        kafkaTemplate.send(topic, message);
    }
}

