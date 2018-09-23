package com.company.MyServerStuff;

import java.util.ArrayList;
import java.util.List;

public class Initiater {
    private List<BroadcastListener> listeners = new ArrayList<>();
    
    public void addListener(BroadcastListener toAdd)
    {
        listeners.add(toAdd);
    }
    
    public void newUserJoined(Client client)
    {
        System.out.println(client + " joined");
        
        for(BroadcastListener broadcastListener : listeners)
        {
            broadcastListener.broadcastNewJoined(client);
        }
        
    }
}
