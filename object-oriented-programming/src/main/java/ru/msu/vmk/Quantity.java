package ru.msu.vmk;

import ru.msu.vmk.figures.Animal;
import ru.msu.vmk.figures.Cat;
import ru.msu.vmk.figures.Dog;
import ru.msu.vmk.figures.Robot;
import ru.msu.vmk.figures.Runnable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

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
            throw new IllegalArgumentException("unitOfMeasurement cannot be empty");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Quantity(String amount, String unitOfMeasurement) {
        this(new BigDecimal(amount), unitOfMeasurement);
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
    public Quantity add(Quantity quantity) {
        validateUnitOfMeasurementAreEqual(quantity);
        BigDecimal sum = this.amount.add(quantity.amount);
        return new Quantity(sum, this.unitOfMeasurement);
    }

    /**
     * Разность двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity вычитаемое значение
     * @return разность чисел
     */
    public Quantity subtract(Quantity quantity) {
        validateUnitOfMeasurementAreEqual(quantity);
        BigDecimal result1 = this.amount.subtract(quantity.amount);
        return new Quantity(result1, this.unitOfMeasurement);
    }

    /**
     * Умножение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity множитель
     * @return произведение
     */
    public Quantity multiply(Quantity quantity) {
        validateUnitOfMeasurementAreEqual(quantity);
        BigDecimal result2 = this.amount.multiply(quantity.amount);
        return new Quantity(result2, this.unitOfMeasurement);
    }

    /**
     * Частное двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity делитель
     * @return частное
     */
    public Quantity divide(Quantity quantity) {
        validateUnitOfMeasurementAreEqual(quantity);
        BigDecimal result3 = this.amount.divide(quantity.amount);
        return new Quantity(result3, this.unitOfMeasurement);
    }

    /**
     * Деление amount на n равных частей
     * если остается остаток, он прибавляется к первому числу
     *
     * @param n делитель
     * @return равные части числа
     */

    public Quantity[] divide(int n) throws Exception {
        if (n<=0) {throw new IllegalArgumentException("Количество частей должно быть больше 0");
        }
        var array = new Quantity[n];
        BigDecimal a = this.amount.divide(BigDecimal.valueOf(n), 2, RoundingMode.HALF_UP);
        BigDecimal difference = this.amount.subtract(a.multiply(BigDecimal.valueOf(n)));

        if (difference.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal a0 = this.amount.remainder(BigDecimal.valueOf(n));
            BigDecimal a1 = this.amount.subtract(a0);
            BigDecimal a2 = a1.divide(BigDecimal.valueOf(n));
            array[0] = new Quantity(a2.add(a0), this.unitOfMeasurement);

            for(int i = 1; i < n; ++i) {
                array[i] = new Quantity(a2, this.unitOfMeasurement);
            }
        }
        else {
            for(int i = 0; i < n; ++i) {
                array[i] = new Quantity(a, this.unitOfMeasurement);
            }
        }
        return array;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity1 = (Quantity) o;

        return amount.equals(quantity1.amount) && unitOfMeasurement.equals(quantity1.unitOfMeasurement);
    }
    private void validateUnitOfMeasurementAreEqual(Quantity quantity) {
        if (!this.unitOfMeasurement.equals(quantity.unitOfMeasurement)) {
            throw new IllegalArgumentException("Единицы измерения не совпадают: " + this.unitOfMeasurement + " " + quantity.unitOfMeasurement);
        }
    }
}
