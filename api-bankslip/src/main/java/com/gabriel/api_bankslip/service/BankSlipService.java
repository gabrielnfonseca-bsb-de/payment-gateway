package com.gabriel.api_bankslip.service;

import com.gabriel.api_bankslip.controller.exception.ApplicationException;
import com.gabriel.api_bankslip.controller.exception.NotFoundException;
import com.gabriel.api_bankslip.dto.BankSlipDTO;
import com.gabriel.api_bankslip.entity.BankSlipEntity;
import com.gabriel.api_bankslip.entity.enums.BankSlipStatus;
import com.gabriel.api_bankslip.mapper.BankSlipMapper;
import com.gabriel.api_bankslip.repository.BankSlipRepository;
import com.gabriel.api_bankslip.service.kafka.BankSlipProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BankSlipService {

    private final BankSlipRepository repository;
    private final BankSlipProducer producer;

    public BankSlipService(BankSlipRepository repository, BankSlipProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public BankSlipDTO save(String barcode) {
        var optional = repository.findByBarcode(barcode);
        if (optional.isPresent()) {
            throw new ApplicationException("A payment request for this bank slip already exists");
        }

        var entity = BankSlipEntity.builder()
                .barcode(barcode)
                .status(BankSlipStatus.INITIALIZED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        repository.save(entity);
        producer.sendMessage(BankSlipMapper.toAvro(entity));
        return BankSlipMapper.toDTO(entity);
    }

    public BankSlipDTO findByBarcode(String barcode) {
        return BankSlipMapper.toDTO(getBankSlip(barcode));
    }

    private BankSlipEntity getBankSlip(String barcode) {
        return repository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException("Bank slip not found"));
    }

    public void update(BankSlipEntity slip) {
        var current = getBankSlip(slip.getBarcode());

        current.setStatus(slip.getStatus());
        current.setUpdatedAt(LocalDateTime.now());
        repository.save(current);
    }
}
