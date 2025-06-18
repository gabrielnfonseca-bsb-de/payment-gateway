package com.gabriel.api_bankslip.service.kafka;

import com.gabriel.avro.BankSlip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BankSlipProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankSlipProducer.class);

    @Value("${spring.kafka.bank-slip-topic}")
    private String topic;

    private final KafkaTemplate<String, BankSlip> kafkaTemplate;

    public BankSlipProducer(KafkaTemplate<String, BankSlip> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(BankSlip bankSlip) {
        String key = getKey(bankSlip);
        LOGGER.info(String.format("Sending message to topic [%s] with key [%s]: %s", topic, key, bankSlip));
        kafkaTemplate.send(topic, key, bankSlip);
    }

    private String getKey(BankSlip bankSlip) {
        if (bankSlip.getBarcode().toString().startsWith("2")) {
            return "key1";
        }
        return "key2";
    }
}
