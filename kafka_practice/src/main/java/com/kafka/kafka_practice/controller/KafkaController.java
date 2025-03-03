package com.kafka.kafka_practice.controller;

import com.kafka.kafka_practice.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    private final KafkaProducerService producerService;


    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String topic,
                                              @RequestParam(required = false) String key,
                                              @RequestParam String message){
        producerService.sendMessage(topic, key, message);
        return ResponseEntity.ok("Message sent to Kafka!");
    }
}
