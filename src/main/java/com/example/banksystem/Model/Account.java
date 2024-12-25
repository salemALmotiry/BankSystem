package com.example.banksystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity


public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 19)
    private String accountNumber;


    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private Boolean isActive = false;



    @ManyToOne
    @JsonIgnore
    private Customer customer;

//    @ManyToOne
//    @JsonIgnore
//    private Employee employee;

}
