package ru.msu.vmk;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

public class NumberSample {

    public static BigDecimal[] split(BigDecimal amount, int n) {
        var result = amount.divide(new BigDecimal(n));
        BigDecimal[] resArray = new BigDecimal[n];
        Arrays.fill(resArray, result);
        return resArray;
    }
}
