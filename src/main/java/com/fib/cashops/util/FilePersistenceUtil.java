package com.fib.cashops.util;

import com.fib.cashops.model.*;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class FilePersistenceUtil {

    private static final Path TX_FILE = Paths.get("transactions.txt");
    private static final Path BALANCE_FILE = Paths.get("balances.txt");

    public static void saveTransaction(Transaction tx) {
        try (BufferedWriter writer = Files.newBufferedWriter(TX_FILE, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(String.format("%s; %s; %s; %s; %s; %s%n%n",
                    tx.getTimestamp(), tx.getCashier(), tx.getType(), tx.getCurrency(),
                    formatDenoms(tx.getDenominations()), tx.getTotal()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveBalance(Map<String, Cashier> cashiers) {
        try (BufferedWriter writer = Files.newBufferedWriter(BALANCE_FILE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Cashier c : cashiers.values()) {
                for (var entry : c.getBalances().entrySet()) {
                    writer.write(String.format("%s; %s; %s%n%n", c.getName(), entry.getKey(), formatDenoms(entry.getValue()) ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatDenoms(Map<Denomination, Integer> map) {
        return map.entrySet().stream()
                .map(e -> e.getKey().getValue() + "x" + e.getValue())
                .reduce((a, b) -> a + "," + b).orElse("");
    }
}