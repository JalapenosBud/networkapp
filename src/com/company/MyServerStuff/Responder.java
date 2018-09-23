package com.company.MyServerStuff;

public class Responder implements BroadcastListener {
    @Override
    public void broadcastNewJoined() {
        System.out.println("Hello there...");
    }
}
