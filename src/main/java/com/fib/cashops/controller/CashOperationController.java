package com.fib.cashops.controller;

import com.fib.cashops.dto.CashOperationRequest;
import com.fib.cashops.dto.CashierBalanceResponse;
import com.fib.cashops.service.CashOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/api/v1")
    @RequiredArgsConstructor
    public class CashOperationController {

        private final CashOperationService service;

        @PostMapping("/cash-operation")
        public ResponseEntity<String> handleCashOperation(@Valid @RequestBody CashOperationRequest request) {
            return ResponseEntity.ok(service.processCashOperation(request));
        }

        @GetMapping("/cash-balance")
        public ResponseEntity<List<CashierBalanceResponse>> getBalances(
                @RequestParam Optional<String> cashier,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dateFrom,
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> dateTo
        ) {
            return ResponseEntity.ok(service.getCashiers(cashier, dateFrom, dateTo));
        }
    }

