package com.example.banksystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity

public class Customer {

    @Id
    private Integer id;


    @Column(nullable = false, length = 10)
    private String phoneNumber;


    @OneToOne(mappedBy = "customer")
    @MapsId
    @JsonIgnore
    private MyUser user;

    @OneToMany
    private Set<Account> accounts;

}
