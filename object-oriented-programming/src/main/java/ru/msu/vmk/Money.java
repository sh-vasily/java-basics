package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        if (amount == null) {
            throw new IllegalArgumentException("amount cannot be null");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("currency cannot be empty");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.currency = currency;
    }

    public Money(String amount, String currency) {
        this(new BigDecimal(amount), currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money m2) {
        validateCurrenciesAreEqual(m2);
        BigDecimal sum = this.amount.add(m2.amount);
        return new Money(sum, this.currency);
    }

    public Money subtract(Money m2) {
        validateCurrenciesAreEqual(m2);
        BigDecimal result = this.amount.subtract(m2.amount);
        return new Money(result, this.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;

        return amount.equals(money.amount) && currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    private void validateCurrenciesAreEqual(Money m2) {
        if (!this.currency.equals(m2.currency)) {
            throw new IllegalArgumentException("Валюты не совпадают: " + this.currency + " " + m2.currency);
        }
    }
}
