package com.kafka.service;


import com.kafka.entity.OrderEntity;

public interface OrderService {

    boolean placeOrder(OrderEntity orderEntity);
}
