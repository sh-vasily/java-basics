package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
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

    private final BigDecimal amount;
    private final String unitOfMeasurement;


    /**
     * @param amount - сумма
     * @param unitOfMeasurement - единица измерения
     */
    public Quantity(BigDecimal amount, String unitOfMeasurement) {
        if (amount == null) {
            throw new IllegalArgumentException("amount cannot be null");
        }
        if (unitOfMeasurement == null || unitOfMeasurement.isBlank()) {
            throw new IllegalArgumentException("currency cannot be empty");
        }
        this.amount = amount;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    /**
     * Get метод для {@link Quantity#amount}
     * @return {@link Quantity#amount}
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Сложение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity прибавляемое значение
     * @return сумма чисел
     */
    public Quantity add(Quantity quantity) throws IllegalArgumentException {
        validateMeasuresAreEqual(quantity);
        return new Quantity(this.amount.add(quantity.amount), this.unitOfMeasurement);
    }

    /**
     * Разность двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity вычитаемое значение
     * @return разность чисел
     */
    public Quantity subtract(Quantity quantity) throws IllegalArgumentException {
        validateMeasuresAreEqual(quantity);
        return new Quantity(this.amount.subtract(quantity.amount), this.unitOfMeasurement);
    }

    /**
     * Умножение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity множитель
     * @return произведение
     */
    public Quantity multiply(Quantity quantity) throws IllegalArgumentException {
        validateMeasuresAreEqual(quantity);
        return new Quantity(this.amount.multiply(quantity.amount, new MathContext(2, RoundingMode.HALF_UP)), this.unitOfMeasurement);
    }

    /**
     * Частное двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity делитель
     * @return частное
     */
    public Quantity divide(Quantity quantity) throws IllegalArgumentException {
        validateMeasuresAreEqual(quantity);
        return new Quantity(this.amount.divide(quantity.amount), this.unitOfMeasurement);
    }

    /**
     * Деление amount на n равных частей
     * если остается остаток, он прибавляется к первому числу
     * @param n делитель
     * @return равные части числа
     */
    public Quantity[] divide(int n) {
        Quantity[] result = new Quantity[n];
        int valueWithoutIntegralScale = 10;
        if (amount.longValue() < valueWithoutIntegralScale) {
            var devisionValue = amount.divide(new BigDecimal(n));
            Arrays.fill(result, new Quantity(devisionValue, unitOfMeasurement));
        } else {
            var devisionValue = amount.divideAndRemainder(new BigDecimal(n));
            Arrays.fill(result, new Quantity(devisionValue[0], unitOfMeasurement));
            result[0] = (new Quantity(devisionValue[0].add(devisionValue[1]), unitOfMeasurement));
        }
        return result;
    }

    private void validateMeasuresAreEqual(Quantity quantity) {
        if (!this.unitOfMeasurement.equals(quantity.unitOfMeasurement)) {
            throw new IllegalArgumentException("Единицы измерения не совпадают: " + this.unitOfMeasurement + " " + quantity.unitOfMeasurement);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity quantity)) return false;
        return Objects.equals(amount, quantity.amount) && Objects.equals(unitOfMeasurement, quantity.unitOfMeasurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unitOfMeasurement);
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "amount=" + amount +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                '}';
    }
}
