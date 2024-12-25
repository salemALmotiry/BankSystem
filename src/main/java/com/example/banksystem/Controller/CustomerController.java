package com.example.banksystem.Controller;


import com.example.banksystem.Api.ApiResponse;
import com.example.banksystem.DTO.IN.Customer.RegisterCustomerDTO;
import com.example.banksystem.DTO.IN.Customer.UpdateCustomerDTO;
import com.example.banksystem.Model.MyUser;
import com.example.banksystem.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }




    @GetMapping("/my-info")
    public ResponseEntity getMyInfo(@AuthenticationPrincipal MyUser myUser) {

        return ResponseEntity.ok().body(customerService.getCustomer(myUser.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterCustomerDTO registerCustomerDTO) {

        customerService.register(registerCustomerDTO);
        return ResponseEntity.ok().body(new ApiResponse("Register successful"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid UpdateCustomerDTO updateCustomerDTO) {

        customerService.update(myUser.getId(), updateCustomerDTO);

        return ResponseEntity.ok().body(new ApiResponse("Update successful"));
    }




}
