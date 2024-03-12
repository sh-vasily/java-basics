package ru.msu.vmk;

import java.math.BigDecimal;

public class NumberSample {

    public static BigDecimal[] split(BigDecimal amount, int n) {
        var array = new BigDecimal[n];
        double temp = amount.doubleValue();
        int previousSize = n;
        for (int i = 0; n > 0; i++, n--) {
            double size = temp / n;
            if(previousSize > 2) {
                if (i == 0) {
                    double ost = temp % n;
                    array[i] = BigDecimal.valueOf((int) size).add(BigDecimal.valueOf(ost));
                }else {
                    array[i] = BigDecimal.valueOf((int)size);
                }
            } else {
                    array[i] = BigDecimal.valueOf(size);
            }
            temp -= size;
        }
        return array;
    }
}
