package com.gabriel.api_bankslip.controller;

import com.gabriel.api_bankslip.dto.BankSlipDTO;
import com.gabriel.api_bankslip.dto.BankSlipRequestDTO;
import com.gabriel.api_bankslip.service.BankSlipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bankslips")
public class BankSlipController {

    private final BankSlipService bankSlipService;

    public BankSlipController(BankSlipService bankSlipService) {
        this.bankSlipService = bankSlipService;
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<BankSlipDTO> getByBarcode(@PathVariable String barcode) {
        var dto = bankSlipService.findByBarcode(barcode);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<BankSlipDTO> create(@Valid @RequestBody BankSlipRequestDTO requestDTO) {
        var created = bankSlipService.save(requestDTO.getBarcode());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
