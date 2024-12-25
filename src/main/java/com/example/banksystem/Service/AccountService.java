package com.example.banksystem.Service;

import com.example.banksystem.Api.ApiException;
import com.example.banksystem.DTO.IN.Account.AccountFundsTransferDTO;
import com.example.banksystem.DTO.IN.Account.AccountNumberDTO;
import com.example.banksystem.DTO.IN.Account.CreateAccountDTO;
import com.example.banksystem.DTO.IN.Account.TransactionOpsDTO;
import com.example.banksystem.DTO.OUT.Account.AccountDetailsDTO;
import com.example.banksystem.DTO.OUT.Account.UserAccountDTO;
import com.example.banksystem.Model.Account;
import com.example.banksystem.Model.Customer;
import com.example.banksystem.Repostiry.AccountRepository;
import com.example.banksystem.Repostiry.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor

public class AccountService {


    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public void createAccount(Integer customerId, CreateAccountDTO createAccountDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);

        if (customer == null) {
            throw new ApiException("Customer with ID " + customerId + " does not exist.");
        }

        Account account = new Account();
        account.setAccountNumber(createAccountDTO.getAccountNumber());
        account.setBalance(createAccountDTO.getBalance());
        account.setIsActive(false);
        account.setCustomer(customer);
        accountRepository.save(account);
    }


    public void activateAccount(AccountNumberDTO accountNumberDTO) {

        Account account = accountRepository.findAccountByAccountNumber(accountNumberDTO.getAccountNumber());

        if (account == null) {
            throw new ApiException("Account number " + accountNumberDTO.getAccountNumber() + " not found.");
        }

        if (account.getIsActive()) {
            throw new ApiException("Account number " + accountNumberDTO.getAccountNumber() + " is already activated.");
        }

        account.setIsActive(true);
        accountRepository.save(account);
    }


    public AccountDetailsDTO getAccountDetails(Integer customerId, AccountNumberDTO accountNumberDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer with ID " + customerId + " does not exist.");
        }


        Account account = accountRepository.findAccountByCustomerIdAndAccountNumber(customerId, accountNumberDTO.getAccountNumber());
        if (account == null) {
            throw new ApiException("Account number " + accountNumberDTO.getAccountNumber() + " not found for customer ID " + customerId + ".");
        }

        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        accountDetailsDTO.setAccountNumber(accountNumberDTO.getAccountNumber());
        accountDetailsDTO.setBalance(account.getBalance());

        return accountDetailsDTO;
    }

    public List<UserAccountDTO> usersAccounts() {

        List<Account> accounts = accountRepository.findAll();

        if (accounts.isEmpty()) {
            throw new ApiException("No accounts available.");
        }

        List<UserAccountDTO> usersAccountsDTOS = new ArrayList<>();
        for (Account account : accounts) {
            UserAccountDTO userAccount = new UserAccountDTO();
            userAccount.setAccountNumber(account.getAccountNumber());
            userAccount.setName(account.getCustomer().getUser().getName());
            usersAccountsDTOS.add(userAccount);
        }


        return usersAccountsDTOS;

    }


    public void deposit(Integer customerId, TransactionOpsDTO transactionOpsDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);

        if (customer == null) {
            throw new ApiException("Customer with ID " + customerId + " does not exist.");
        }

        Account account = accountRepository.findAccountByCustomerIdAndAccountNumber(customerId, transactionOpsDTO.getAccountNumber());

        if (account == null) {
            throw new ApiException("Account number " + transactionOpsDTO.getAccountNumber() + " not found for customer ID " + customerId + ".");
        }

        if (!account.getIsActive()) {
            throw new ApiException("Account number " + transactionOpsDTO.getAccountNumber() + " is not active. Please visit the nearest branch to activate your account.");
        }

        account.setBalance(account.getBalance().add(transactionOpsDTO.getAmount()));
        accountRepository.save(account);
    }

    public void withdraw(Integer customerId, TransactionOpsDTO transactionOpsDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer with ID " + customerId + " does not exist.");
        }

        Account account = accountRepository.findAccountByCustomerIdAndAccountNumber(customerId, transactionOpsDTO.getAccountNumber());
        if (account == null) {
            throw new ApiException("Account number " + transactionOpsDTO.getAccountNumber() + " not found for customer ID " + customerId + ".");
        }

        if (!account.getIsActive()) {
            throw new ApiException("Account number " + transactionOpsDTO.getAccountNumber() + " is not active. Please visit the nearest branch to activate your account.");
        }

        if (account.getBalance().compareTo(transactionOpsDTO.getAmount()) < 0) {
            throw new ApiException("Insufficient balance in account number " + transactionOpsDTO.getAccountNumber() + ".");
        }

        account.setBalance(account.getBalance().subtract(transactionOpsDTO.getAmount()));
        accountRepository.save(account);
    }

    public void transfer(Integer customerId, AccountFundsTransferDTO accountFundsTransferDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer with ID " + customerId + " does not exist.");
        }

        String sourceAccountNumber = accountFundsTransferDTO.getSourceAccountNumber();
        String destinationAccountNumber = accountFundsTransferDTO.getDestinationAccountNumber();
        BigDecimal transferAmount = accountFundsTransferDTO.getTransferAmount();

        Account sourceAccount = accountRepository.findAccountByCustomerIdAndAccountNumber(customerId, sourceAccountNumber);
        if (sourceAccount == null) {
            throw new ApiException("Source account number " + sourceAccountNumber + " not found for customer ID " + customerId + ".");
        }

        if (!sourceAccount.getIsActive()) {
            throw new ApiException("Source account number " + sourceAccountNumber + " is not active. Please visit the nearest branch to activate your account.");
        }

        Account destinationAccount = accountRepository.findAccountByAccountNumber(destinationAccountNumber);
        if (destinationAccount == null) {
            throw new ApiException("Destination account number " + destinationAccountNumber + " not found.");
        }

        if (!destinationAccount.getIsActive()) {
            throw new ApiException("Destination account number " + destinationAccountNumber + " is not active.");
        }

        withdraw(customerId, new TransactionOpsDTO(sourceAccountNumber, transferAmount));
        deposit(destinationAccount.getCustomer().getId(), new TransactionOpsDTO(destinationAccountNumber, transferAmount));
    }


    public void deactivateAccount(AccountNumberDTO accountNumberDTO) {
        String accountNumber = accountNumberDTO.getAccountNumber();
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);

        if (account == null) {
            throw new ApiException("Account number " + accountNumber + " not found.");
        }

        if (!account.getIsActive()) {
            throw new ApiException("Account number " + accountNumber + " is already deactivated.");
        }

        account.setIsActive(false);
        accountRepository.save(account);
    }


    public void blockCustomer(Integer customerId) {
        List<Account> accounts = accountRepository.findAccountByCustomerId(customerId);

        if (accounts == null || accounts.isEmpty()) {
            throw new ApiException("No accounts found for customer ID " + customerId + ".");
        }

        for (Account account : accounts) {
            if (account.getIsActive()) {
                account.setIsActive(false);
                accountRepository.save(account);
            }
        }
    }


}
