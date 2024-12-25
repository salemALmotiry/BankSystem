package com.example.banksystem.DTO.OUT.Customer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDTO {

    private String username;

    private String name;

    private String email;


    private String phoneNumber;

}
