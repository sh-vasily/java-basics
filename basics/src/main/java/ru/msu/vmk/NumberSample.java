package ru.msu.vmk;

import java.math.BigDecimal;

public class NumberSample {
    public static BigDecimal[] split(BigDecimal amount, int n) {
        var array = new BigDecimal[n];
        double temp = amount.doubleValue();
        for (int i = 0; n > 0; i++, n--) {
            double size = temp / n;
            array[i] = BigDecimal.valueOf(size);
            temp -= size;
        }
        return array;
    }
}
