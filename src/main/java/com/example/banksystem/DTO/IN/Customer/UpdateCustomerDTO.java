package com.example.banksystem.DTO.IN.Customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerDTO {

    @NotEmpty(message = "Username is mandatory")
    @Size(min = 4, max = 30, message = "Username must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Phone number is mandatory")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with '05' and be 10 digits long")
    private String phoneNumber;
}
