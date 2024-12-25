package com.example.banksystem.Service;


import com.example.banksystem.Api.ApiException;
import com.example.banksystem.DTO.IN.Customer.RegisterCustomerDTO;
import com.example.banksystem.DTO.IN.Customer.UpdateCustomerDTO;
import com.example.banksystem.DTO.OUT.Customer.CustomerInfoDTO;
import com.example.banksystem.Model.Customer;
import com.example.banksystem.Model.MyUser;
import com.example.banksystem.Repostiry.AuthRepository;
import com.example.banksystem.Repostiry.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;


    public CustomerInfoDTO getCustomer(Integer customerId) {
       MyUser myUser = authRepository.findMyUserById(customerId);

        if (myUser == null) {
            throw new ApiException("Customer with ID " + customerId + " does not exist.");
        }

        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();

        customerInfoDTO.setName(myUser.getName());
        customerInfoDTO.setEmail(myUser.getEmail());
        customerInfoDTO.setUsername(myUser.getUsername());
        customerInfoDTO.setPhoneNumber(myUser.getCustomer().getPhoneNumber());
        return customerInfoDTO;

    }

    public void register(RegisterCustomerDTO registerCustomerDTO) {

        MyUser myUser = new MyUser();
        Customer customer = new Customer();

        myUser.setUsername(registerCustomerDTO.getUsername());
        myUser.setName(registerCustomerDTO.getName());
        myUser.setEmail(registerCustomerDTO.getEmail());
        myUser.setPassword(new BCryptPasswordEncoder().encode(registerCustomerDTO.getPassword()));
        myUser.setRole("CUSTOMER");

        authRepository.save(myUser);


        customer.setPhoneNumber(registerCustomerDTO.getPhoneNumber());
        customer.setUser(myUser);

        customerRepository.save(customer);



    }


    public void update(Integer customerId,UpdateCustomerDTO updateCustomerDTO) {

        MyUser myUser = authRepository.findMyUserById(customerId);

        if (myUser == null) {
            throw new ApiException("Customer with ID " + customerId + " does not exist.");
        }

        myUser.setUsername(updateCustomerDTO.getUsername());
        myUser.setName(updateCustomerDTO.getName());
        myUser.setEmail(updateCustomerDTO.getEmail());
        myUser.getCustomer().setPhoneNumber(updateCustomerDTO.getPhoneNumber());

        authRepository.save(myUser);

    }


}
