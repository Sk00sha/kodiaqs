package org.example.runner;

public class Environment {
    private String name;
    private int coresAvailable;
    public static Environment getRunner(){
        return new Environment();
    }
}
