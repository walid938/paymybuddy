package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.model.Bankaccount;
import com.wayl.paymybuddy.model.dto.BankaccountDto;

import java.math.BigDecimal;

public interface BankaccountService {
    Boolean saveOrUpdate(BankaccountDto accountDto, String email);
    Bankaccount findByUserId(int id);
    Boolean sendMoney(Bankaccount fromAcc, Bankaccount toAcc, BigDecimal amount, String description);

}
