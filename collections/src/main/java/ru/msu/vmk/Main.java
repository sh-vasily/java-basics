package ru.msu.vmk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        var set = new HashSet<String>();

        set.add("test");
        set.add("test1");

        for (var element: set){
         //   System.out.println(element);
        }

        var map = new HashMap<Integer, String>();

        map.putIfAbsent(1, "test1");
        map.putIfAbsent(1, "test3");
        map.putIfAbsent(2, "test2");


        for (var key: map.keySet()){
            System.out.println("key=" + key + " value=" + map.get(key));
        }

        var array = new ArrayList<String>();

        array.add("first");
        array.add("second");
        array.add("third");

        array.add(1, "modified");
        System.out.println(array.set(1, "modified1"));

        for (var element: array){
            System.out.println("index = " + array.indexOf(element) + " element =" + element);
        }

        var deque = new ArrayDeque<String>();

        deque.offer("first");
        deque.offer("second");
        deque.offer("third");

        while (!deque.isEmpty()){
            var element = deque.removeLast();
            System.out.println(element);
        }

        var stack = new Stack<String>();

        stack.push("first");
        stack.push("second");
        stack.push("third");

        while (!stack.isEmpty()){
            var element = stack.pop();
            System.out.println(element);
        }
    }
}