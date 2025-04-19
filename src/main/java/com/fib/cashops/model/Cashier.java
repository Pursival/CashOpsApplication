package com.fib.cashops.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;

public class Cashier {
    private final String name;
    private final Map<Currency, Map<Denomination, Integer>> balances = new ConcurrentHashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();

    public Cashier(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public Map<Currency, Map<Denomination, Integer>> getBalances() { return balances; }
    public List<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction tx) {
        transactions.add(tx);
    }

    public BigDecimal getTotalBalance(Currency currency) {
        return balances.getOrDefault(currency, Map.of()).entrySet().stream()
                .map(e -> e.getKey().getValue().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}