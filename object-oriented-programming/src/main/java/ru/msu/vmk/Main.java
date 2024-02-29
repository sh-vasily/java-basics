package ru.msu.vmk;

import ru.msu.vmk.figures.*;
import ru.msu.vmk.figures.Runnable;

public class Main {
    public static void main(String[] args) {
        var cat = new Cat("cat 1");
        var cat2 = new Cat("cat 2");
        var cat3 = new Cat();
        var dog = new Dog("dog 1", "corgy");
        var dog2 = new Dog("dog 2", "haski");
        var dog3 = new Dog();
        var robot= new Robot();

        var animals = new Animal[]{ cat, cat2, cat3, dog, dog2, dog3 };
        var runnables = new Runnable[]{ cat, cat2, cat3, dog, dog2, dog3, robot };
        var cats = new Animal[]{ cat, cat2, cat3 };
        printAnimals(animals);
        //run(runnables);
    }

    public static void run(Runnable[] runnables){
        for (var runnable : runnables){
            runnable.run();
        }
    }

    public static void printAnimals(Animal[] animals){
        for (var animal : animals){
            System.out.println(animal);
        }
    }

    public static void printAnimals(Cat[] animals){
        for (var animal : animals){
            System.out.println(animal);
        }
    }

    public static void printAnimals(Dog[] animals){
        for (var animal : animals){
            System.out.println(animal);
        }
    }
}