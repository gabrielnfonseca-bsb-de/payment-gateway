package com.gabriel.api_bankslip.mapper;

import com.gabriel.avro.BankSlip;
import com.gabriel.api_bankslip.dto.BankSlipDTO;
import com.gabriel.api_bankslip.entity.BankSlipEntity;
import com.gabriel.api_bankslip.entity.enums.BankSlipStatus;

public class BankSlipMapper {

    public static BankSlipDTO toDTO(BankSlipEntity entity) {
        return BankSlipDTO.builder()
                .barcode(entity.getBarcode())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static BankSlipEntity toEntity(BankSlip bankSlip) {
        return BankSlipEntity.builder()
                .barcode(bankSlip.getBarcode().toString())
                //.status(SlipStatus.values()[bankSlip.getStatus()])
                .status(BankSlipStatus.values()[bankSlip.getStatus()])
                .build();
    }

    public static BankSlip toAvro(BankSlipEntity entity) {
        return BankSlip.newBuilder()
                .setBarcode(entity.getBarcode())
                .setStatus(entity.getStatus().ordinal())
                .build();
    }
}
