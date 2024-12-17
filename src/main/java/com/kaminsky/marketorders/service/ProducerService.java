package com.kaminsky.marketorders.service;

import com.kaminsky.marketorders.entity.NewOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final KafkaTemplate<String, NewOrder> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, NewOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, NewOrder order)
    {
        kafkaTemplate.send(topic, order);
    }
}
