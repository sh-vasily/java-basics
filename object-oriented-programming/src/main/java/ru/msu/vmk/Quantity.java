package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * По примеру класса {@link Money} реализовать класс Quantity:
 * <br/>- с поддержкой единиц измерения {@link Quantity#Quantity(BigDecimal, String)}
 * <br/> - арифметической операции {@link Quantity#add(Quantity)}
 * <br/> - арифметической операции {@link Quantity#subtract(Quantity)}
 * <br/> - арифметической операции {@link Quantity#multiply(Quantity)}
 * <br/> - арифметической операции {@link Quantity#divide(Quantity)}
 * <br/> - реализовать операцию {@link Quantity#divide(int)} деления на N равных частей с добавлением остатка к первому значению (см. предыдущее задание)
 * <br/> - для проверки работы реализовать функцию main() или Unit-test
 */
public class Quantity {
    /**
     * @param amount - сумма
     * @param unitOfMeasurement - единица измерения
     */
    private final BigDecimal amount;
    private final String unitOfMeasurement;

    public Quantity(BigDecimal amount, String unitOfMeasurement) {
        if (amount == null) {
            throw new IllegalArgumentException("amount cannot be null");
        }
        if (unitOfMeasurement == null || unitOfMeasurement.isBlank()) {
            throw new IllegalArgumentException("currency cannot be empty");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.unitOfMeasurement = unitOfMeasurement;
    }

    /**
     * Get метод для {@link Quantity#amount}
     * @return {@link Quantity#amount}
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Сложение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity прибавляемое значение
     * @return сумма чисел
     */
    public Quantity add(Quantity quantity) throws Exception {
        validateCurrenciesAreEqual(quantity);
        BigDecimal sum = this.amount.add(quantity.amount);
        return new Quantity(sum, this.unitOfMeasurement);
    }

    /**
     * Разность двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity вычитаемое значение
     * @return разность чисел
     */
    public Quantity subtract(Quantity quantity) throws Exception {
        validateCurrenciesAreEqual(quantity);
        BigDecimal result = this.amount.subtract(quantity.amount);
        return new Quantity(result, this.unitOfMeasurement);
    }

    /**
     * Умножение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity множитель
     * @return произведение
     */
    public Quantity multiply(Quantity quantity) throws Exception {
        validateCurrenciesAreEqual(quantity);
        BigDecimal result = this.amount.multiply(quantity.amount);
        return new Quantity(result, this.unitOfMeasurement);
    }

    /**
     * Частное двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity делитель
     * @return частное
     */
    public Quantity divide(Quantity quantity) throws Exception {
        validateCurrenciesAreEqual(quantity);
        BigDecimal result = this.amount.divide(quantity.amount, RoundingMode.HALF_EVEN);
        return new Quantity(result, this.unitOfMeasurement);
    }

    /**
     * Деление amount на n равных частей
     * если остается остаток, он прибавляется к первому числу
     * @param n делитель
     * @return равные части числа
     */
    public Quantity[] divide(int n) throws Exception {
        return Arrays.stream(NumberSample.split(this.amount, n)).map(bigDecimal -> new Quantity(bigDecimal, this.unitOfMeasurement)).toArray(Quantity[]::new);
    }

    private void validateCurrenciesAreEqual(Quantity quantity) {
        if (!this.unitOfMeasurement.equals(quantity.unitOfMeasurement)) {
            throw new IllegalArgumentException("Валюты не совпадают: " + this.unitOfMeasurement + " " + quantity.unitOfMeasurement);
        }
    }

}
