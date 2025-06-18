package br.com.validator.payment_validator.service;
import br.com.validator.payment_validator.entity.PaymentEntity;
import br.com.validator.payment_validator.entity.enums.PaymentStatus;
import br.com.validator.payment_validator.mapper.PaymentMapper;
import br.com.validator.payment_validator.repository.PaymentRepository;
import br.com.validator.payment_validator.service.kafka.NotificationProducer;
import com.gabriel.avro.BankSlip;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentProcessingService {

    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;

    public PaymentProcessingService(PaymentRepository paymentRepository, NotificationProducer notificationProducer) {
        this.paymentRepository = paymentRepository;
        this.notificationProducer = notificationProducer;
    }

    @SneakyThrows
    public void process(PaymentEntity payment) {
        Thread.sleep(10000); // Simulates external processing delay
        String barcodeNumbers = payment.getBarcode().replaceAll("[^0-9]", "");

        if (barcodeNumbers.length() > 47) {
            markAsFailed(payment);
        } else {
            markAsPaid(payment);
        }

        paymentRepository.save(payment);
        notificationProducer.sendMessage(PaymentMapper.toAvro(payment));
    }

    private void markAsFailed(PaymentEntity payment) {
        payment.setUpdatedAt(LocalDateTime.now());
        //payment.setPaymentStatus(PaymentStatus.PAYMENT_FAILED);
        payment.setPaymentStatus(PaymentStatus.VALIDATION_ERROR);
    }

    private void markAsPaid(PaymentEntity payment) {
        payment.setUpdatedAt(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.PAID);
    }
}
