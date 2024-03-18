package ru.msu.vmk;

import java.math.BigDecimal;

public class NumberSample {
    public static BigDecimal[] split(BigDecimal amount, int n) {
        // разделить amount на n частей
        // если остается остаток, прибавить его к первому числу
        var array = new BigDecimal[n];
        double temp = amount.doubleValue();
        int previousSize = n;
        for (int i = 0; n > 0; i++, n--) {
            double size = temp / n;
            if (previousSize > 2) {
                array[i] = BigDecimal.valueOf((int) size);
            } else {
                array[i] = BigDecimal.valueOf(size);
            }
            temp -= size;
        }
        if (previousSize > 2) {
            array[0] = BigDecimal.valueOf((int) (amount.doubleValue() / previousSize)).add(BigDecimal.valueOf(amount.doubleValue() % previousSize));
        }
        return array;
    }
}
