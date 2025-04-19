package com.fib.cashops.model;

import java.math.BigDecimal;

public class Denomination {
    private final BigDecimal value;

    public Denomination(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Denomination)) return false;
        Denomination that = (Denomination) o;
        return value.compareTo(that.value) == 0;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}