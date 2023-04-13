package org.example.transactions.controller;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import org.example.BasicController;
import org.example.transactions.dto.Account;
import org.example.transactions.dto.Transaction;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TransactionController extends BasicController<List<Transaction>> {

    public TransactionController() {
        super(new TypeToken<List<Transaction>>() {
        }.getType());
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Transaction> transactionList = fromJson(exchange);

        List<Account> accountList = new LinkedList<>();
        for (Transaction transaction : transactionList) {
            Account debitAccount = getOrCreateDebitAccount(accountList, transaction);
            Account creditAccount = getOrCreateCreditAccount(accountList, transaction);

            debitAccount.subtractBalance(transaction.getAmount());
            creditAccount.addBalance(transaction.getAmount());

            accountList.remove(debitAccount);
            accountList.add(debitAccount);
            accountList.remove(creditAccount);
            accountList.add(creditAccount);
        }

        Collections.sort(accountList);

        buildResponse(accountList, exchange);
    }

    private Account getOrCreateCreditAccount(List<Account> accountList, Transaction transaction) {
        Account debitAccount = accountList
                .stream()
                .filter(it -> it.getAccount().equals(transaction.getCreditAccount()))
                .findFirst().orElse(null);
        if (Objects.isNull(debitAccount)) {
            debitAccount = new Account(transaction.getCreditAccount());
        }
        return debitAccount;
    }

    private Account getOrCreateDebitAccount(List<Account> accountList, Transaction transaction) {
        Account debitAccount = accountList
                .stream()
                .filter(it -> it.getAccount().equals(transaction.getDebitAccount()))
                .findFirst().orElse(null);
        if (Objects.isNull(debitAccount)) {
            debitAccount = new Account(transaction.getDebitAccount());
        }
        return debitAccount;
    }
}
