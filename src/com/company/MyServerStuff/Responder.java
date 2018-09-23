package com.company.MyServerStuff;

public class Responder implements BroadcastListener {
    @Override
    public void broadcastNewJoined(Client client) {
        System.out.println("Hello there " + client);
    }
}
