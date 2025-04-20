package com.fib.cashops.dto;

import com.fib.cashops.model.Transaction;

import java.util.List;
import java.util.Map;

public class CashierBalanceResponse {
    private String name;
    private Map<String, Map<String, Object>> balances; // Includes denominations and totals
    private List<Transaction> transactions;

    public CashierBalanceResponse(String name, Map<String, Map<String, Object>> balances, List<Transaction> transactions) {
        this.name = name;
        this.balances = balances;
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public Map<String, Map<String, Object>> getBalances() {
        return balances;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}