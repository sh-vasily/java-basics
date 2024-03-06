package ru.msu.vmk;

import java.math.BigDecimal;

public class NumberSample {
    public static BigDecimal[] split(BigDecimal amount, int n) {
        // разделить amount на n частей
        // если остается остаток, прибавить его к первому числу
        BigDecimal[] val = new BigDecimal[n];

        for (int i = 0; i < n; ++i){
            val[i] = amount.divide(BigDecimal.valueOf(n));
        }
        return val;
    }
}
