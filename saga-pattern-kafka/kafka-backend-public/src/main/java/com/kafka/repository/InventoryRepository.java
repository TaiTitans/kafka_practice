package com.kafka.repository;

import com.kafka.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InventoryRepository extends JpaRepository<OrderEntity, Long> {
}
