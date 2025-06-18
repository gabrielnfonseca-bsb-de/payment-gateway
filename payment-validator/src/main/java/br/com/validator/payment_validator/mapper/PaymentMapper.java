package br.com.validator.payment_validator.mapper;

import com.gabriel.avro.BankSlip;
import br.com.validator.payment_validator.entity.PaymentEntity;
import br.com.validator.payment_validator.entity.enums.PaymentStatus;

public class PaymentMapper {

    public static PaymentEntity toEntity(BankSlip bankSlip) {
        return PaymentEntity.builder()
                .barcode(bankSlip.getBarcode().toString())
                .paymentStatus(PaymentStatus.values()[bankSlip.getStatus()])
                .build();
    }

    public static BankSlip toAvro(PaymentEntity entity) {
        return BankSlip.newBuilder()
                .setBarcode(entity.getBarcode())
                .setStatus(entity.getPaymentStatus().ordinal())
                .build();
    }
}
