package br.com.validator.payment_validator.service.kafka;

import com.gabriel.avro.BankSlip;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Value("${spring.kafka.notification-topic}")
    private String topic;

    private final KafkaTemplate<String, BankSlip> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, BankSlip> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(BankSlip bankSlip) {
        this.kafkaTemplate.send(topic, bankSlip);
    }
}
