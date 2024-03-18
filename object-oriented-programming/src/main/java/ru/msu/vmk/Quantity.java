package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (unitOfMeasurement == null || unitOfMeasurement.isBlank()) {
            throw new IllegalArgumentException("unit Of Measurement cannot be empty");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.unitOfMeasurement = unitOfMeasurement;
    }

    /**
     * Get метод для {@link Quantity#amount}
     *
     * @return {@link Quantity#amount}
     */

    public BigDecimal getAmount() {
        return amount;
    }

    public String getunitOfMeasurement() {
        return unitOfMeasurement;
    }

    /**
     * Сложение двух чисел в одной единице измерения
     *
     * @param quantity прибавляемое значение
     * @return сумма чисел
     * @throws Exception в случае различных единиц измерения
     */
    public Quantity add(Quantity q2) throws Exception {
        checkUnitCompatibility(q2);
        BigDecimal sum = this.amount.add(q2.amount);
        return new Quantity(sum, this.unitOfMeasurement);
    }

    /**
     * Разность двух чисел в одной единице измерения
     *
     * @param quantity вычитаемое значение
     * @return разность чисел
     * @throws Exception в случае различных единиц измерения
     */
    public Quantity subtract(Quantity q2) throws Exception {
        checkUnitCompatibility(q2);
        BigDecimal difference = this.amount.subtract(q2.amount);
        return new Quantity(difference, this.unitOfMeasurement);
    }

    /**
     * Умножение двух чисел в одной единице измерения
     *
     * @param quantity множитель
     * @return произведение
     * @throws Exception в случае различных единиц измерения
     */
    public Quantity multiply(Quantity q2) throws Exception {
        checkUnitCompatibility(q2);
        return new Quantity(this.amount.multiply(q2.amount), this.unitOfMeasurement);
    }

    /**
     * Частное двух чисел в одной единице измерения
     *
     * @param quantity делитель
     * @return частное
     * @throws Exception в случае различных единиц измерения
     */
    public Quantity divide(Quantity q2) throws Exception {
        checkUnitCompatibility(q2);
        if (q2.amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new Quantity(this.amount.divide(q2.amount, 2, RoundingMode.HALF_UP), this.unitOfMeasurement);
    }

    /**
     * Деление amount на n равных частей
     * если остается остаток, он прибавляется к первому числу
     *
     * @param n делитель
     * @return равные части числа
     */
    public Quantity[] divide(int n) throws Exception {
        if (n <= 0) {
            throw new IllegalArgumentException("The divider must be a positive number.");
        }
        var array = new Quantity[n];
        BigDecimal a = this.amount.divide(BigDecimal.valueOf(n), 2, RoundingMode.HALF_UP);
        BigDecimal difference = this.amount.subtract(a.multiply(BigDecimal.valueOf(n)));

        if (difference.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal a0 = this.amount.remainder(BigDecimal.valueOf(n));
            BigDecimal a1 = this.amount.subtract(a0);
            BigDecimal a2 = a1.divide(BigDecimal.valueOf(n));
            array[0] = new Quantity(a2.add(a0), this.unitOfMeasurement);
            for (int i = 1; i < n; i++) {
                array[i] = new Quantity(a2, this.unitOfMeasurement);
            }
        } else {
            for (int i = 0; i < n; i++) {
                array[i] = new Quantity(a, this.unitOfMeasurement);
            }
        }
        return array;
    }

    private void checkUnitCompatibility(Quantity q2) {
        if (!this.unitOfMeasurement.equals(q2.unitOfMeasurement)) {
            throw new IllegalArgumentException("Units are incompatible: " + this.unitOfMeasurement + " " + q2.unitOfMeasurement);
        }
    }
}