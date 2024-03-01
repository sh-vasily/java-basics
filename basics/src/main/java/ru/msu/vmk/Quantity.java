package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Quantity {

    private final BigDecimal amount;
    private final String currency;

    public Quantity(BigDecimal amount, String currency) {
        if (amount == null) {
            throw new IllegalArgumentException("amount cannot be null");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("currency cannot be empty");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.currency = currency;
    }

    public Quantity(String amount, String currency) {
        this(new BigDecimal(amount), currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    /***
     * Method add amount
     * @param quantity - finance object
     * @return - new finance object
     */
    public Quantity add(Quantity quantity) {
        validateCurrenciesAreEqual(quantity);
        BigDecimal sum = this.amount.add(quantity.amount);
        return new Quantity(sum, this.currency);
    }

    /***
     * Method subtract amount
     * @param quantity - finance object
     * @return - new finance object
     */
    public Quantity subtract(Quantity quantity) {
        validateCurrenciesAreEqual(quantity);
        BigDecimal result = this.amount.subtract(quantity.amount);
        return new Quantity(result, this.currency);
    }

    /***
     * Method multiply amount
     * @param quantity - finance object
     * @return - new finance objet
     */
    public Quantity multiply(Quantity quantity) {
        validateCurrenciesAreEqual(quantity);
        BigDecimal result = this.amount.multiply(quantity.amount);
        return new Quantity(result, this.currency);
    }

    /***
     * Method divide amount by n equals parts
     * @param n - part segment
     * @return - array BigDecimal[]
     */
    public BigDecimal[] divide(int n) {
        return NumberSample.split(this.amount, n);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return amount.equals(quantity.amount) && currency.equals(quantity.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    private void validateCurrenciesAreEqual(Quantity quantity) {
        if (!this.currency.equals(quantity.currency)) {
            throw new IllegalArgumentException("Валюты не совпадают: " + this.currency + " " + quantity.currency);
        }
    }
}
