package ru.msu.vmk;

import java.math.BigDecimal;

public class NumberSample {
    public static void main(String[] args) {
        // вызов split - проверка работы
        BigDecimal amount = new BigDecimal("100");
        int n = 3;
        BigDecimal[] parts = split(amount, n);
        for (BigDecimal part : parts) {
            System.out.println(part);
        }

    }
    public static BigDecimal[] split(BigDecimal amount, int n) {
        var result = amount.divide(new BigDecimal(n));
        var reminder = amount.remainder(new BigDecimal(n));
        var array = new BigDecimal[n];
        for (int i=0; i < n; i++) {
            array[i] = result;
        }

        // разделить amount на n частей
        // если остается остаток, прибавить его к первому числу
        return array;
    }
}
