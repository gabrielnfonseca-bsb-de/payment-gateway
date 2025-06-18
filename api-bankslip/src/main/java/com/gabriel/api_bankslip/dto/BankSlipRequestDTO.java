package com.gabriel.api_bankslip.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankSlipRequestDTO {

    @NotNull(message = "must not be null")
    @NotEmpty(message = "must not be empty")
    private String barcode;
}
