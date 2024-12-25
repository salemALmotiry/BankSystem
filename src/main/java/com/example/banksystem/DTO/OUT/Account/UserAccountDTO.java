package com.example.banksystem.DTO.OUT.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {

    private String name;
    private String accountNumber;
}
