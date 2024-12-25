package com.example.banksystem.Controller;


import com.example.banksystem.Api.ApiResponse;
import com.example.banksystem.DTO.IN.Employee.RegisterEmployeeDTO;
import com.example.banksystem.DTO.IN.Employee.UpdateEmployeeDTO;
import com.example.banksystem.Model.MyUser;
import com.example.banksystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;


    @GetMapping("/my-info")
    public ResponseEntity getMyInfo(@AuthenticationPrincipal MyUser myUser) {

        return ResponseEntity.ok().body(employeeService.getEmployee(myUser.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterEmployeeDTO employeeDTO) {
        employeeService.register(employeeDTO);
        return ResponseEntity.ok().body(new ApiResponse("Employee registered successfully"));

    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid UpdateEmployeeDTO updateEmployeeDTO) {

        employeeService.update(myUser.getId(), updateEmployeeDTO);

        return ResponseEntity.ok().body(new ApiResponse("Update successful"));
    }


}
