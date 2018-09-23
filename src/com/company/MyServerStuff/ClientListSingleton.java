package com.company.MyServerStuff;

import java.util.ArrayList;

public class ClientListSingleton {
    
    private ArrayList<Client> clients = new ArrayList<>();
    
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
    
    public void addClient(Client client)
    {
        clients.add(client);
        client.broadcast();
    }
    
    public Client getLastPersonAdded()
    {
        return clients.get(clients.size() - 1);
    }
    
}
