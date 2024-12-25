package com.example.banksystem.Controller;


import com.example.banksystem.Api.ApiResponse;
import com.example.banksystem.DTO.IN.Account.AccountFundsTransferDTO;
import com.example.banksystem.DTO.IN.Account.AccountNumberDTO;
import com.example.banksystem.DTO.IN.Account.CreateAccountDTO;
import com.example.banksystem.DTO.IN.Account.TransactionOpsDTO;
import com.example.banksystem.Model.MyUser;
import com.example.banksystem.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {


    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity createAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid CreateAccountDTO createAccountDTO) {

        accountService.createAccount(user.getId(), createAccountDTO);
        return ResponseEntity.ok().body(new ApiResponse("Account created"));

    }


    @PutMapping("/activate")
    public ResponseEntity activateAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid AccountNumberDTO accountNumberDTO) {

        accountService.activateAccount(accountNumberDTO);
        return ResponseEntity.ok().body(new ApiResponse("Account activated"));
    }

    @GetMapping("/account-details")
    public ResponseEntity getAccountDetails(@AuthenticationPrincipal MyUser user,@RequestBody @Valid AccountNumberDTO accountNumberDTO) {

        return ResponseEntity.ok().body(accountService.getAccountDetails(user.getId(), accountNumberDTO));
    }

    @GetMapping("/users-accounts")
    public ResponseEntity usersAccounts(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.ok().body(accountService.usersAccounts());
    }

    @PutMapping("/deposit")
    public ResponseEntity depositAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid TransactionOpsDTO transactionOpsDTO) {

        accountService.deposit(user.getId(), transactionOpsDTO);
        return ResponseEntity.ok().body(new ApiResponse("Account deposited"));
    }

    @PutMapping("/withdraw")
    public ResponseEntity withdrawAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid TransactionOpsDTO transactionOpsDTO) {

        accountService.withdraw(user.getId(), transactionOpsDTO);
        return ResponseEntity.ok().body(new ApiResponse("Account withdraw"));
    }

    @PutMapping("/transfer")
    public ResponseEntity transfer(@AuthenticationPrincipal MyUser user, @RequestBody @Valid AccountFundsTransferDTO accountFundsTransferDTO) {

        accountService.transfer(user.getId(), accountFundsTransferDTO);

        return ResponseEntity.ok().body(new ApiResponse("transfer success with : " + accountFundsTransferDTO.getTransferAmount()));
    }


    @PutMapping("/deactivate")
    public ResponseEntity deactivateAccount(@AuthenticationPrincipal MyUser user, @RequestBody @Valid AccountNumberDTO accountNumberDTO) {

        accountService.deactivateAccount(accountNumberDTO);
        return ResponseEntity.ok().body(new ApiResponse("Account deactivate"));
    }

    @PutMapping("/block/{customerId}")
    public ResponseEntity blockCustomer(@AuthenticationPrincipal MyUser user,@PathVariable Integer customerId) {

        accountService.blockCustomer(customerId);
        return ResponseEntity.ok().body(new ApiResponse("Customer blocked"));
    }
}


