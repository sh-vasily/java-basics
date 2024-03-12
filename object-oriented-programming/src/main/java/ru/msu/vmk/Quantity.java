package ru.msu.vmk;

import java.math.BigDecimal;

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
    public Quantity(BigDecimal amount, String unitOfMeasurement) {
    }

    /**
     * Get метод для {@link Quantity#amount}
     * @return {@link Quantity#amount}
     */
    public BigDecimal getAmount() {
        return null;
    }

    /**
     * Сложение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity прибавляемое значение
     * @return сумма чисел
     */
    public Quantity add(Quantity quantity) throws Exception {
        return null;
    }

    /**
     * Разность двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity вычитаемое значение
     * @return разность чисел
     */
    public Quantity subtract(Quantity quantity) throws Exception {
        return null;
    }

    /**
     * Умножение двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity множитель
     * @return произведение
     */
    public Quantity multiply(Quantity quantity) throws Exception {
        return null;
    }

    /**
     * Частное двух чисел в одной единице измерения
     * @throws Exception в случае различных единиц измерения
     * @param quantity делитель
     * @return частное
     */
    public Quantity divide(Quantity quantity) throws Exception {
        return null;
    }

    /**
     * Деление amount на n равных частей
     * если остается остаток, он прибавляется к первому числу
     * @param n делитель
     * @return равные части числа
     */
    public Quantity[] divide(int n) throws Exception {
        return null;
    }
}
