package com.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dto.OrderRequest;
import com.kafka.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private KafkaTemplate<String, String> kafkaPaymentTemplate;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaOrderTemplate;
    @KafkaListener(topics = "order-new", groupId = "myGroup")
    public void placePaymentProcess(String event) throws JsonProcessingException {
        log.info("Payment Recieved event" + event);
        OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
        OrderRequest order = orderEvent.getOrderRequest();
//        Payment payment = new Payment();
        try {
            // save payment details in db
//            payment.setAmount(order.getAmount());
//            payment.setMode(order.getPaymentMode());
//            payment.setOrderId(order.getOrderId());
//            payment.setStatus("SUCCESS");
//            this.repository.save(payment);
//            // publish payment created event for inventory microservice to consume.
//            PaymentEvent paymentEvent = new PaymentEvent();
//            paymentEvent.setOrder(orderEvent.getOrder());
//            paymentEvent.setType("PAYMENT_CREATED");
//            this.kafkaTemplate.send("new-payments", paymentEvent);


            // success or failed
            if (order.getOrderId() % 2 == 0) {
                try {
                    Thread.sleep(3000); // Dừng chương trình 10 giây (10,000 milliseconds)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int er = 100 / 0;
            }else{
                log.info("Payment Success>>");
                this.kafkaPaymentTemplate.send("new-payments", "next-inventory");
            }


        } catch (Exception e) {
            //if error -> update dbs oayment FAILED
//            payment.setOrderId(order.getOrderId());
//            payment.setStatus("FAILED");
//            repository.save(payment);
            // reverse previous task

            // Error
            OrderEvent oEvent = new OrderEvent();
            oEvent.setOrderRequest(order);
            oEvent.setType("ORDER_REVERSED");
            this.kafkaOrderTemplate.send("reversed-orders", orderEvent);
        }
    }

}
