package com.wayl.paymybuddy.model.dto;

import com.wayl.paymybuddy.model.Bankaccount;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class TransactionDto {
    private Bankaccount bankuserAccount;
    @Setter
    @Getter
    private String toEmail;
    private BigDecimal amount;
    private String description;

    public Bankaccount getUserAccount() {
        return bankuserAccount;
    }

    public void setUserAccount(Bankaccount bankuserAccount) {
        this.bankuserAccount = bankuserAccount;
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