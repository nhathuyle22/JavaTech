package com.example.sneakershop.service;

import com.example.sneakershop.model.Account;
import com.example.sneakershop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(int id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public boolean checkIfExistUsername(String username) {
        List<Account> accounts = accountRepository.findAll();
        for (Account account: accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfExistEmail(String email) {
        List<Account> accounts = accountRepository.findAll();
        for (Account account: accounts) {
            if (account.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Account findByUsername(String username) {
        for (Account account: accountRepository.findAll()) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
}
