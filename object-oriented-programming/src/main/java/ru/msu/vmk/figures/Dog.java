package ru.msu.vmk.figures;

public class Dog extends Animal {
    public Dog(String dogName, String type){
        super(dogName);
        super.type = type;
    }
    public Dog(){
        super("default dog name");
        super.type = "default dog type";
    }

    @Override
    public void run() {
        System.out.println("Dog run");
    }

    @Override
    public String toString() {
        return String.join (" ","Dog with name", getName(),"and type", type);
    }
}
