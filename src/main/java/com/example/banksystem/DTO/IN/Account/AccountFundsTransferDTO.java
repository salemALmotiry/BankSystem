package com.example.banksystem.DTO.IN.Account;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountFundsTransferDTO {

    @NotEmpty(message = "Source account number is mandatory")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$", message = "Source account number must follow the format XXXX-XXXX-XXXX-XXXX")
    private String sourceAccountNumber;

    @NotEmpty(message = "Destination account number is mandatory")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$", message = "Destination account number must follow the format XXXX-XXXX-XXXX-XXXX")
    private String destinationAccountNumber;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal transferAmount;
}
