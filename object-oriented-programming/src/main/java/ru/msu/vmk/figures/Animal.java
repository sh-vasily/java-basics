package ru.msu.vmk.figures;

public abstract class Animal implements Runnable {
    private String name;
    protected String type;

/*    public Animal(){
        this.name = "cat name";
    }*/

    public Animal(String catName){
        name = catName;
    }

    protected String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
