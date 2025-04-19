package com.fib.cashops.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Map;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAWAL }

    private final Type type;
    private final Currency currency;
    private final Map<Denomination, Integer> denominations;
    private final LocalDateTime timestamp;
    private final String cashier;

    public Transaction(Type type, Currency currency, Map<Denomination, Integer> denominations, String cashier) {
        this.type = type;
        this.currency = currency;
        this.denominations = denominations;
        this.timestamp = LocalDateTime.now();
        this.cashier = cashier;
    }

    public Type getType() { return type; }
    public Currency getCurrency() { return currency; }
    public Map<Denomination, Integer> getDenominations() { return denominations; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getCashier() { return cashier; }

    public BigDecimal getTotal() {
        return denominations.entrySet().stream()
                .map(e -> e.getKey().getValue().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}