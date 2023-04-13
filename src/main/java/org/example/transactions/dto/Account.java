package org.example.transactions.dto;

import org.example.Constants;

import java.math.BigDecimal;

public class Account implements Comparable<Account> {
    private final String account;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance;

    public Account(String account) {
        this.account = account;
        this.debitCount = Constants.INT_ZERO;
        this.creditCount = Constants.INT_ZERO;
        this.balance = new BigDecimal(Constants.INT_ZERO);
    }

    public String getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account1 = (Account) o;

        return account.equals(account1.account);
    }

    @Override
    public int hashCode() {
        return account.hashCode();
    }

    public void subtractBalance(BigDecimal balance) {
        this.balance = this.balance.subtract(balance);
        debitCount++;
    }

    public void addBalance(BigDecimal balance) {
        this.balance = this.balance.add(balance);
        creditCount++;
    }

    @Override
    public int compareTo(Account o) {
        return this.account.compareTo(o.getAccount());
    }
}
