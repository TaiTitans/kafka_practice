package com.kafka.controller;


import com.kafka.dto.OrderRequest;
import com.kafka.entity.OrderEntity;
import com.kafka.event.OrderEvent;
import com.kafka.repository.OrderRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order/old")
    public void createOrderOld(@RequestBody OrderRequest orderRequest) {
        OrderEntity order = new OrderEntity();
        try {
            // save order in database
            order.setAmount(orderRequest.getAmount());
            order.setItem(orderRequest.getItem());
            order.setQuantity(orderRequest.getQuantity());
            order.setStatus("CREATED");
            order = orderRepository.save(order);
            orderRequest.setOrderId(order.getId());
            // publish order created event for payment microservice to consume.
            OrderEvent event = new OrderEvent();
            event.setOrderRequest(orderRequest);
            event.setType("ORDER_CREATED");
            kafkaTemplate.send("order-new", event);
        } catch (Exception e) {
            log.error(e.getMessage());
            order.setStatus("FAILED");
            orderRepository.save(order);
        }
    }


    @PostMapping("/order/new")
    public void createOrder(@RequestBody OrderRequest orderRequest) {
        OrderEntity order = new OrderEntity();
        try {
            // save order in database
            order.setAmount(orderRequest.getAmount());
            order.setItem(orderRequest.getItem());
            order.setQuantity(orderRequest.getQuantity());
            order.setStatus("CREATED");
            order = orderRepository.save(order);
            orderRequest.setOrderId(order.getId());
            // publish order created event for payment microservice to consume.
            OrderEvent event = new OrderEvent();
            event.setOrderRequest(orderRequest);
            event.setType("ORDER_CREATED");
            kafkaTemplate.send("order-new", event);
        } catch (Exception e) {
            log.error(e.getMessage());
            order.setStatus("FAILED");
            orderRepository.save(order);
        }
    }
}
