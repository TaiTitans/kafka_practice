package com.kafka.kafka_practice.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String key, String message){
        CompletableFuture<RecordMetadata> future = kafkaTemplate.send(topic , key, message).thenApply(result -> result.getRecordMetadata());

        future.whenComplete((metadata, exception)->{
           if(exception == null){
               System.out.println("Send message=["+message+"] with offset=["+metadata.offset()+"]");
           }else{
               System.err.println("Failed to send message=["+message+"] due to: "+exception.getMessage());
           }
        });
    }
}
