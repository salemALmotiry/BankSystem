package com.example.banksystem.DTO.OUT.Account;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDTO {

    private String accountNumber;

    private BigDecimal balance;
}
