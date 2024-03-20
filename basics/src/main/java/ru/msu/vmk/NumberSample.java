package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberSample {
    public static BigDecimal[] split(BigDecimal amount, int n) {
        // разделить amount на n частей
        // если остается остаток, прибавить его к первому числу
        BigDecimal[] result = new BigDecimal[n];
        BigDecimal splittedEqualAmount = amount.divide(new BigDecimal(n), 2, RoundingMode.DOWN)
        .stripTrailingZeros().movePointRight(1).movePointLeft(1);
        
        for (int i = 0; i < n; i++) {
            result[i] = splittedEqualAmount;
        }

        return result;
    }
}