package com.service;

import com.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account select_(String code);

    int insert_(Account account);
}
