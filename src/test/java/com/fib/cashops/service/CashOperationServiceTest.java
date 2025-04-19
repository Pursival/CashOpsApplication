package com.fib.cashops.service;

import com.fib.cashops.dto.CashOperationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CashOperationServiceTest {

    private CashOperationService service;

    @BeforeEach
    public void setup() {
        service = new CashOperationService();
    }

    @Test
    public void testDepositSuccess() {
        CashOperationRequest req = new CashOperationRequest();
        req.setCashier("MARTINA");
        req.setCurrency("BGN");
        req.setType("DEPOSIT");
        req.setDenominations(Map.of("50", 5, "10", 10));

        String response = service.processCashOperation(req);
        assertEquals("Success", response);
    }

    @Test
    public void testUnknownCashier() {
        CashOperationRequest req = new CashOperationRequest();
        req.setCashier("UNKNOWN");
        req.setCurrency("BGN");
        req.setType("DEPOSIT");
        req.setDenominations(Map.of("10", 1));

        String response = service.processCashOperation(req);
        assertEquals("Cashier not found", response);
    }
}