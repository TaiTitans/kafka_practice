package com.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.entity.OrderEntity;
import com.kafka.event.OrderEvent;
import com.kafka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReverseOrder {
    @Autowired
    private OrderRepository orderRepository;
    @KafkaListener(topics = "reversed-orders", groupId = "myGroup")
    public void reverseOrder(String event) {
        try {
            OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
            Optional<OrderEntity> order = this.orderRepository.findById(orderEvent.getOrderRequest().getOrderId());
            // if exist in database
            order.ifPresent(o -> {
                o.setStatus("FAILED_BY_SAGA");
                this.orderRepository.save(o);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
