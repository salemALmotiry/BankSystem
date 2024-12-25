package com.example.banksystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Employee {

    @Id
    private Integer id;


    @Column(nullable = false)
    private String position;


    @Column(nullable = false)
    private BigDecimal salary;

    @OneToOne(mappedBy = "employee")
    @MapsId
    @JsonIgnore
    private MyUser user;

//    @OneToMany
//    private Set<Account> accounts;


}
