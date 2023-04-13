package org.example.transactions.dto;

import java.math.BigDecimal;

public class Transaction {
    private final String debitAccount;
    private final String creditAccount;
    private final BigDecimal amount;

    public Transaction(String debitAccount, String creditAccount, double amount) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = new BigDecimal(amount);
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
