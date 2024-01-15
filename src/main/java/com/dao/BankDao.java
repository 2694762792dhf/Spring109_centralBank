package com.dao;

import com.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDao {

    Account select_(String code);

    int insert_(Account account);
}
