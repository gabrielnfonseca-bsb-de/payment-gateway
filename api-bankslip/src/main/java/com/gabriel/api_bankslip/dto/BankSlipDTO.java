package com.gabriel.api_bankslip.dto;

import com.gabriel.api_bankslip.entity.enums.BankSlipStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankSlipDTO {

    private String barcode;
    private BankSlipStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
