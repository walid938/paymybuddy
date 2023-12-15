package com.wayl.paymybuddy.model.dto;

import com.wayl.paymybuddy.model.Bankaccount;

import java.math.BigDecimal;

public class TransactionDto {
    private Bankaccount userBankaccount;
    private String toEmail;
    private BigDecimal amount;
    private String description;

    public Bankaccount getUserBankaccount() {
        return userBankaccount;
    }

    public void setUserBankaccount(Bankaccount userBankaccount) {
        this.userBankaccount = userBankaccount;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}