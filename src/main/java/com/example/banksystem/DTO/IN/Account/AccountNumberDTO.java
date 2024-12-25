package com.example.banksystem.DTO.IN.Account;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountNumberDTO {
    @NotEmpty(message = "Account number is mandatory")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$", message = "Account number must follow the numeric format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;

}
