package ru.msu.vmk;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberSample {
    public static BigDecimal[] split(BigDecimal amount, int n) {
        // разделить amount на n частей
        // если остается остаток, прибавить его к первому числу
        BigDecimal[] val = new BigDecimal[n];
        BigDecimal remainder;

        for (int i = 0; i < n; ++i){
            try {
                val[i] = amount.divide(BigDecimal.valueOf(n));
            } catch (Exception e) {
//                System.out.println(e);
//                remainder = BigDecimal.valueOf(0.0);
//                if (i == 0) {
//                    remainder = amount.subtract(amount.divide(BigDecimal.valueOf(n), 0, RoundingMode.DOWN).multiply(BigDecimal.valueOf(n)));
//                }
                remainder = ((i != 0) ? BigDecimal.valueOf(0.0) :
                        amount.subtract(amount.divide(BigDecimal.valueOf(n), 0, RoundingMode.DOWN).multiply(BigDecimal.valueOf(n))));
                val[i] = amount.divide(BigDecimal.valueOf(n), 0, RoundingMode.DOWN).add(remainder);
            }
        }
        return val;
    }
}
