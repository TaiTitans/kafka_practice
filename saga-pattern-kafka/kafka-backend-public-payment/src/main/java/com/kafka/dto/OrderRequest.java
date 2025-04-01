package com.kafka.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long orderId;
    private String item;
    private int quantity;
    private double amount;
}
