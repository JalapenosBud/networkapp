package com.company.MyServerStuff;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class MyServer {

    private ArrayList<Client> clients = new ArrayList<>();
    
    public MyServer()
    {
        int port = 1234;
        boolean listening = true;
        int clientNo = 0;
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            while(listening)
            {
                new MyServerMultiThread(serverSocket.accept(),clientNo).start();
                clients.add(new Client("MyName", clientNo++));
                for (Client client :
                        clients) {
                    System.out.println(client.getName() + " " + client.getNumber());
                }
            }
            
            
        }catch (IOException e)
        {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
    
}
