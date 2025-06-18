package br.com.validator.payment_validator.service.kafka;

import br.com.validator.payment_validator.mapper.PaymentMapper;
import br.com.validator.payment_validator.service.PaymentValidationService;
import com.gabriel.avro.BankSlip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);
    private final PaymentValidationService paymentValidationService;

    public PaymentConsumer(PaymentValidationService paymentValidationService) {
        this.paymentValidationService = paymentValidationService;
    }

    @KafkaListener(topics = "${spring.kafka.topico-boleto}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(BankSlip bankSlip, Acknowledgment ack) throws InterruptedException {
        Thread.sleep(10000);
        LOGGER.info(String.format("Consuming message -> %s", bankSlip));
        //paymentValidationService.validate(PaymentConsumer.toEntity(bankSlip));
        paymentValidationService.validate(PaymentMapper.toEntity(bankSlip));
        ack.acknowledge();
    }
}
