package com.fib.cashops.service;

import com.fib.cashops.dto.CashOperationRequest;
import com.fib.cashops.model.*;
import com.fib.cashops.util.FilePersistenceUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CashOperationService {

    private final Map<String, Cashier> cashiers = new HashMap<>();

    public CashOperationService() {
        initializeCashiers();
    }

    private void initializeCashiers() {
        List<String> names = List.of("MARTINA", "PETER", "LINDA");
        for (String name : names) {
            Cashier c = new Cashier(name);
            Currency bgn = Currency.getInstance("BGN");
            Currency eur = Currency.getInstance("EUR");

            Map<Denomination, Integer> bgnDenoms = new HashMap<>();
            bgnDenoms.put(new Denomination(BigDecimal.valueOf(50)), 10);
            bgnDenoms.put(new Denomination(BigDecimal.valueOf(10)), 50);
            c.getBalances().put(bgn, bgnDenoms);

            Map<Denomination, Integer> eurDenoms = new HashMap<>();
            eurDenoms.put(new Denomination(BigDecimal.valueOf(100)), 10);
            eurDenoms.put(new Denomination(BigDecimal.valueOf(20)), 50);
            c.getBalances().put(eur, eurDenoms);

            cashiers.put(name, c);
        }
    }

    public String processCashOperation(CashOperationRequest request) {
        Cashier cashier = cashiers.get(request.getCashier());
        if (cashier == null) return "Cashier not found";

        Currency currency = Currency.getInstance(request.getCurrency());
        Map<Denomination, Integer> balance = cashier.getBalances().computeIfAbsent(currency, k -> new HashMap<>());

        Map<Denomination, Integer> inputDenoms = request.getDenominations().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> new Denomination(new BigDecimal(e.getKey())),
                        Map.Entry::getValue
                ));

        Transaction.Type type = Transaction.Type.valueOf(request.getType().toUpperCase());
        Transaction tx = new Transaction(type, currency, inputDenoms, request.getCashier());

        for (Map.Entry<Denomination, Integer> e : inputDenoms.entrySet()) {
            balance.merge(e.getKey(), type == Transaction.Type.DEPOSIT ? e.getValue() : -e.getValue(), Integer::sum);
        }

        cashier.addTransaction(tx);
        FilePersistenceUtil.saveTransaction(tx);
        FilePersistenceUtil.saveBalance(cashiers);
        return "Success";
    }

    public List<Cashier> getCashiers(Optional<String> name, Optional<LocalDate> from, Optional<LocalDate> to) {
        return cashiers.values().stream()
                .filter(c -> name.map(n -> c.getName().equalsIgnoreCase(n)).orElse(true))
                .collect(Collectors.toList());
    }
}