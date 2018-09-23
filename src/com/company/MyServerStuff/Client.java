package com.company.MyServerStuff;

public class Client {
    private String name = "";
    private int number;
    
    public Client(String name, int number)
    {
        this.name = name;
        this.number = number;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    @Override
    public String toString() {
        return name + "" + number;
    }
}
