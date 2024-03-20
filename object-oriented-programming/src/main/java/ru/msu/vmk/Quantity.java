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

    private BigDecimal amount;
    private String unitOfMeasurement;

    /**
     * @param amount - сумма
     * @param unitOfMeasurement - единица измерения
     */
    public Quantity(BigDecimal amount, String unitOfMeasurement) {
        if (amount == null) {
            throw new IllegalArgumentException("amount cannot be null");
        }
        if (unitOfMeasurement == null || unitOfMeasurement.isBlank()) {
            throw new IllegalArgumentException("units cannot be empty");
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Сложение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity прибавляемое значение
     * @return сумма чисел
     */
    public Quantity add(Quantity quantity) throws Exception {
        validateEqualUnitOfMeasurement(quantity);
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
        validateEqualUnitOfMeasurement(quantity);
        BigDecimal diff = this.amount.subtract(quantity.amount);
        return new Quantity(diff, this.unitOfMeasurement);
    }

    /**
     * Умножение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity множитель
     * @return произведение
     */
    public Quantity multiply(Quantity quantity) throws Exception {
        validateEqualUnitOfMeasurement(quantity);
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
        // No check because quantity.amount cannot be null
        validateEqualUnitOfMeasurement(quantity);
        BigDecimal result = this.amount.divide(quantity.amount);
        return new Quantity(result, this.unitOfMeasurement);
    }

    /**
     * Деление amount на n равных частей
     * если остается остаток, он прибавляется к первому числу
     * @param n делитель
     * @return равные части числа
     */
    public Quantity[] divide(int n) throws Exception {
        if (n == 0) {
            throw new IllegalArgumentException("Невозможно разделить на 0 частей");
        }

        BigDecimal[] splitted = NumberSample.split(this.amount, n);
        Quantity[] quantityResult = new Quantity[n];
        for (int i = 0; i < n; i++) {
            quantityResult[i] = new Quantity(splitted[i], this.unitOfMeasurement);
        }
        return quantityResult;
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

    private void validateEqualUnitOfMeasurement(Quantity qRight) {
        if (!this.unitOfMeasurement.equals(qRight.unitOfMeasurement)) {
            throw  new IllegalArgumentException("Единицы измерения не совпадают: "
                    + this.unitOfMeasurement + " " + qRight.unitOfMeasurement);
        }
    }
}
