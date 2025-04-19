
// DTO
package com.fib.cashops.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Data
public class CashOperationRequest {
    @NotBlank(message = "Cashier name is required")
    private String cashier;

    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^(BGN|EUR)$", message = "Currency must be BGN or EUR")
    private String currency;

    @NotBlank(message = "Operation type is required")
    @Pattern(regexp = "^(DEPOSIT|WITHDRAWAL)$", message = "Type must be DEPOSIT or WITHDRAWAL")
    private String type;

    @NotEmpty(message = "At least one denomination must be provided")
    private Map<String, Integer> denominations;
}
