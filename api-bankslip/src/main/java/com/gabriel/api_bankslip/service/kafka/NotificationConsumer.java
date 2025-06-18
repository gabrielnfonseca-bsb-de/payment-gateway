package com.gabriel.api_bankslip.service.kafka;

import com.gabriel.api_bankslip.mapper.BankSlipMapper;
import com.gabriel.api_bankslip.service.BankSlipService;
import com.gabriel.avro.BankSlip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);

    private final BankSlipService bankSlipService;

    public NotificationConsumer(BankSlipService bankSlipService) {
        this.bankSlipService = bankSlipService;
    }

    @KafkaListener(topics = "${spring.kafka.notification-topic}")
    public void consume(@Payload BankSlip bankSlip) {
        LOGGER.info(String.format("Consuming notification -> %s", bankSlip));
        bankSlipService.update(BankSlipMapper.toEntity(bankSlip));
    }
}
