package br.com.munhosdev.picpay.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferDTO(

        @NotNull
        @DecimalMin("0.01")
        BigDecimal value,
        @NotNull
        Long payer,
        @NotNull
        Long payee

) {
}
