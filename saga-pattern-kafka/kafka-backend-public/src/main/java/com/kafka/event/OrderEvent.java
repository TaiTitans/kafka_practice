package com.kafka.event;

import com.kafka.dto.OrderRequest;
import lombok.Data;

@Data
public class OrderEvent {
    private String type;
    private OrderRequest orderRequest;
}
