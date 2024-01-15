package com.wayl.paymybuddy.model.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BankaccountDto {

    private Integer accountId;
    private BigDecimal balance;

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}