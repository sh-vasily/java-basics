package ru.msu.vmk;

import java.lang.reflect.Array;
import java.math.BigDecimal;

public class NumberSample {

    public static BigDecimal[] split(BigDecimal amount, int n) {
        var result = amount.divide(new BigDecimal(n));
        return new BigDecimal[]{result, result};
    }
}
