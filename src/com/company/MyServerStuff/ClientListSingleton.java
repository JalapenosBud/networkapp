package com.company.MyServerStuff;

import java.util.ArrayList;

public class ClientListSingleton {
    
    public ArrayList<Client> clients = new ArrayList<>();
    
    private static ClientListSingleton instance;
    
    private ClientListSingleton()
    {
    
    }
    
    public static synchronized ClientListSingleton getInstance()
    {
        if(instance == null)
        {
            instance = new ClientListSingleton();
        }
        return instance;
    }
    
}
