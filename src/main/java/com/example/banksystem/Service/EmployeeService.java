package com.example.banksystem.Service;


import com.example.banksystem.Api.ApiException;
import com.example.banksystem.DTO.IN.Employee.RegisterEmployeeDTO;
import com.example.banksystem.DTO.IN.Employee.UpdateEmployeeDTO;
import com.example.banksystem.DTO.OUT.Employee.EmployeeInfoDTO;
import com.example.banksystem.Model.Employee;
import com.example.banksystem.Model.MyUser;
import com.example.banksystem.Repostiry.AuthRepository;
import com.example.banksystem.Repostiry.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;


    public EmployeeInfoDTO getEmployee(Integer id) {
        MyUser myUser = authRepository.findMyUserById(id);
        if (myUser == null) throw new ApiException("Employee Not Found");

        EmployeeInfoDTO employeeInfoDTO = new EmployeeInfoDTO();

        employeeInfoDTO.setName(myUser.getName());
        employeeInfoDTO.setEmail(myUser.getEmail());
        employeeInfoDTO.setUsername(myUser.getUsername());
        employeeInfoDTO.setPosition(myUser.getEmployee().getPosition());
        employeeInfoDTO.setSalary(myUser.getEmployee().getSalary());
        return employeeInfoDTO;


    }


    public void register(RegisterEmployeeDTO registerEmployeeDTO) {
        MyUser myUser = new MyUser();
        Employee employee = new Employee();

        myUser.setUsername(registerEmployeeDTO.getUsername());
        myUser.setName(registerEmployeeDTO.getName());
        myUser.setEmail(registerEmployeeDTO.getEmail());
        myUser.setPassword(new BCryptPasswordEncoder().encode(registerEmployeeDTO.getPassword()));
        myUser.setRole("EMPLOYEE");

        authRepository.save(myUser);

        employee.setPosition(registerEmployeeDTO.getPosition());
        employee.setSalary(registerEmployeeDTO.getSalary());
        employee.setUser(myUser);
        employeeRepository.save(employee);


    }

    public void update(Integer employeeId, UpdateEmployeeDTO updateEmployeeDTO) {

        MyUser myUser = authRepository.findMyUserById(employeeId);

        if (myUser == null) {
            throw new ApiException("Employee with ID " + employeeId + " does not exist.");
        }

        myUser.setUsername(updateEmployeeDTO.getUsername());
        myUser.setName(updateEmployeeDTO.getName());
        myUser.setEmail(updateEmployeeDTO.getEmail());

        myUser.getEmployee().setPosition(updateEmployeeDTO.getPosition());
        myUser.getEmployee().setSalary(updateEmployeeDTO.getSalary());

        authRepository.save(myUser);
    }

}
