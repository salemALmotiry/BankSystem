package com.example.banksystem.DTO.OUT.Employee;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfoDTO {


    private String username;


    private String name;


    private String email;


    private String position;

    private BigDecimal salary;
}
