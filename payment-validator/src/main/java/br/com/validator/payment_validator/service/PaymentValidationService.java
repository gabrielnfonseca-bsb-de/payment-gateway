package br.com.validator.payment_validator.service;

import br.com.validator.payment_validator.entity.PaymentEntity;
import br.com.validator.payment_validator.entity.enums.PaymentStatus;
import br.com.validator.payment_validator.mapper.PaymentMapper;
import br.com.validator.payment_validator.repository.PaymentRepository;
import br.com.validator.payment_validator.service.kafka.NotificationProducer;
import com.gabriel.avro.BankSlip;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentValidationService {

    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;
    private final PaymentProcessingService paymentProcessingService;

    public PaymentValidationService(PaymentRepository paymentRepository,
                                    NotificationProducer notificationProducer,
                                    PaymentProcessingService paymentProcessingService) {
        this.paymentRepository = paymentRepository;
        this.notificationProducer = notificationProducer;
        this.paymentProcessingService = paymentProcessingService;
    }

    public void validate(PaymentEntity payment) {
        int code = Integer.parseInt(payment.getBarcode().substring(0, 1));

        if (code % 2 == 0) {
            markValidationError(payment);
            paymentRepository.save(payment);
            notificationProducer.sendMessage(PaymentMapper.toAvro(payment));
        } else {
            markValidated(payment);
            paymentRepository.save(payment);
            notificationProducer.sendMessage(PaymentMapper.toAvro(payment));
            paymentProcessingService.process(payment);
        }
    }

    private void markValidationError(PaymentEntity payment) {
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.VALIDATION_ERROR);
    }

    private void markValidated(PaymentEntity payment) {
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.VALIDATED);
    }
}