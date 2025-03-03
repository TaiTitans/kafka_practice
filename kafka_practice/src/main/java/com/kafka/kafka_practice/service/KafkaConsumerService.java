package com.kafka.kafka_practice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private DeadLetterService deadLetterService;


//    Nếu có lỗi, message sẽ được gửi vào DLQ topic (my-topic-dlq).
//    Sau đó, một consumer khác có thể đọc và xử lý lại message từ DLQ.
    @KafkaListener(topics = "my-topic", groupId = "my-consumer-group")
    public void consumeMessage(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            System.out.println("Processing message: " + record.value());
            // Xử lý message
            ack.acknowledge();
        } catch (Exception e) {
            System.err.println("Error processing message, sending to DLQ: " + record.value());
            deadLetterService.sendToDLQ("my-topic-dlq", record.value());
        }
    }
}
