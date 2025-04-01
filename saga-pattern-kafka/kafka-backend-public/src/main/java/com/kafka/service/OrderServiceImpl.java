package com.kafka.service;

import com.kafka.entity.OrderEntity;
import com.kafka.repository.InventoryRepository;
import com.kafka.repository.OrderRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional // Rollback vs commit
    public boolean placeOrder(OrderEntity orderEntity) {
        try {
            //1. Process order
            orderRepository.save(orderEntity);
            //2. process payment
            inventoryRepository.save(orderEntity);
        } catch (Exception e) {
            System.out.println("Error..");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // manual
            e.printStackTrace();
        }
        return true;
    }
}
