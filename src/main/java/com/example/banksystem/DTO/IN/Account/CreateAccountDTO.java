package com.example.banksystem.DTO.IN.Account;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class CreateAccountDTO {


    @NotEmpty(message = "Account number is mandatory")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$", message = "Account number must follow the numeric format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;

    @NotNull(message = "Balance is mandatory")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be a non-negative decimal number")
    private BigDecimal balance;

}
