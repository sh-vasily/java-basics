package ru.msu.vmk;

import java.math.BigDecimal;

public class Main4Quantity {
    public static void main(String[] args) throws Exception {
        Quantity q1 = new Quantity(BigDecimal.valueOf(10.0), "кг");
        Quantity q2 = new Quantity(BigDecimal.valueOf(8.0), "руб");

        int n = 3;

        System.out.println("Сумма: " + q1 + " - " + q2 + " = " + q1.add(q2));
        System.out.println("Разность: " + q1 + " - " + q2 + " = " + q1.subtract(q2));
        System.out.println("Произведение: " + q1 + " - " + q2 + " = " + q1.multiply(q2));
        System.out.println("Деление: " + q1 + " - " + q2 + " = " + q1.divide(q2));
        System.out.println("Деление на N частей: " + q1 + " на " + n + normalaze_text(n) + "разделится следующим образом\n"
                + buildStringFromParts(q1.divide(n)));
    }

    public static   String buildStringFromParts(Quantity[] q) {
        String result = "           [";
        for (Quantity elemnt: q) {
            result = result + elemnt + "; ";
        }

        result = result.substring(0, result.length() - 2) + "]";
        return result;
    }

    public static String normalaze_text(int n) {
        String result = "";

        int remainder;
        if (n > 20) {
            remainder = n % 10;
        } else remainder = n;

        if (remainder == 1) {
            result = " часть ";
        } else if (remainder >= 2 && remainder <= 4) {
            result = " части ";
        } else if (remainder >= 5 && remainder <= 20) {
            result = " частей ";
        }
        return result;
    }
}
