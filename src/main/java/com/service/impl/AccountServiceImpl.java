package com.service.impl;

import com.dao.BankDao;
import com.model.Account;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BankDao bankDao;
    @Override
    public Account select_(String code) {
        Account account = bankDao.select_(code);
        return account;
    }

    @Override
    public int insert_(Account account) {
        int i = bankDao.insert_(account);
        return i;
    }
}
