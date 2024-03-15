package ru.msu.vmk;

import java.math.BigDecimal;
import java.util.Arrays;

public class NumberSample {

    public static BigDecimal[] split(BigDecimal amount, int n) {
        // разделить amount на n частей
        // если остается остаток, прибавить его к первому числу
        var array = new BigDecimal[n];
        //var k = amount.scale();
        BigDecimal sum = BigDecimal.valueOf(0.0);
        //var a = amount.divide(BigDecimal.valueOf(n), k,6);
        var a = amount.divide(BigDecimal.valueOf(n));

        for(int i = 1; i < n; ++i) {
            array[i] = a;
            sum = sum.add(array[i]);
        }
        array[0] = amount.subtract(sum);

       /* var a1 = amount.divide(BigDecimal.valueOf(n));
        var a0 = a1.add(amount.remainder(BigDecimal.valueOf(n)));
        array[0] = a0;

        for(int i = 1; i < n; ++i){
            array[i] = a1;
        }*/

        return array;
    }
}
