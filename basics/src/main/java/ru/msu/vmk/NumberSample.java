package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class NumberSample {
    public static BigDecimal[] split(BigDecimal amount, int n) {
        // разделить amount на n частей
        // если остается остаток, прибавить его к первому числу
        BigDecimal dividedEqualAmount = amount.divide(new BigDecimal(n), 2, RoundingMode.DOWN)
                                        .stripTrailingZeros().movePointRight(1).movePointLeft(1);
        BigDecimal remainder = amount.subtract(dividedEqualAmount.multiply(new BigDecimal(n)));

        BigDecimal[] result = new BigDecimal[n];
        Arrays.fill(result, dividedEqualAmount);
        result[0].add(remainder).stripTrailingZeros().movePointRight(1).movePointLeft(1);

        return result;
    }
}