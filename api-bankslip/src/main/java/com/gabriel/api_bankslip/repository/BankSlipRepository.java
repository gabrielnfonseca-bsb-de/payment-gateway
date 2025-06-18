package com.gabriel.api_bankslip.repository;

import com.gabriel.api_bankslip.entity.BankSlipEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankSlipRepository extends CrudRepository<BankSlipEntity, Long> {
    Optional<BankSlipEntity> findByBarcode(String barcode);
}
