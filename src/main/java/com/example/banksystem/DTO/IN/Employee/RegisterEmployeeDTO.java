package com.example.banksystem.DTO.IN.Employee;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployeeDTO {

    @NotEmpty(message = "Username is mandatory")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Position is mandatory")
    private String position;

    @NotNull(message = "Salary is mandatory")
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary must be a non-negative decimal number")
    private BigDecimal salary;
}
