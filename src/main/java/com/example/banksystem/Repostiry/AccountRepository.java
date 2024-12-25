package com.example.banksystem.Repostiry;

import com.example.banksystem.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountById(Integer id);
    Account findAccountByAccountNumber(String accountNumber);


    List<Account> findAccountByCustomerId(Integer customerId);

    Account findAccountByCustomerIdAndAccountNumber(Integer customerId, String accountNumber);

}
