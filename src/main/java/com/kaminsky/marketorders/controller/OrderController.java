package com.kaminsky.marketorders.controller;

import com.kaminsky.entity.MarketOrder;
import com.kaminsky.status.Status;
import com.kaminsky.marketorders.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@RestController
public class OrderController {
    private final ProducerService producerService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/")
    public ResponseEntity<String> publishMessage(@RequestBody MarketOrder order) {
        order.setId(UUID.randomUUID());

        if (order.getStatus() == null) {
            order.setStatus(Status.IN_PROGRESS);
        }

        producerService.sendMessage("new_orders", order);
        logger.info("Sent new order: {}", order);
        return ResponseEntity.ok("Order published to new_orders topic");
    }
}
