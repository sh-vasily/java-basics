package ru.msu.vmk.figures;

public class Cat extends Animal {
    public Cat(String catName){
        super(catName);
    }
    public Cat(){
        super("default cat name");
    }

    @Override
    public void run() {
        System.out.println("Cat run");
    }
}
