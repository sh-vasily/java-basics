package ru.msu.vmk;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пожалуйста, введите размер массива.");
        var scanner = new Scanner(System.in);
        var arraySize = scanner.nextInt();
        var array = new int[arraySize];

        for (int i = 0; i < arraySize; ++i){
            System.out.printf("Пожалуйста, введите элемент массива номер %d.\n", i);
            array[i] = scanner.nextInt();
        }

        var sum = sum(array);
        System.out.printf("Сумма массива равна %d.\n", sum);
    }

    private static int sum(int[] array){
        var sum = 0;
        for (int i = 0; i < array.length; ++i){
            sum += array[i];
        }
        return sum;
    }
}