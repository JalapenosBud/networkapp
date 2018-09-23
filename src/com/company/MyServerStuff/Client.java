package com.company.MyServerStuff;

public class Client implements Broadcast{
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
    
    @Override
    public void broadcast() {
        
    
    }
    
    @Override
    public String tellNewClientHasJoined() {
       return this.toString() + " has joined the channel.";
    }
}
