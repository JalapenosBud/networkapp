package com.company.MyServerStuff;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class MyServer {

    private ArrayList<Client> clients = new ArrayList<>();
    
    Initiater initiater = new Initiater();
    Responder responder = new Responder();
    int clientNo = 0;
    
    public MyServer()
    {
        initiater.addListener(responder);
        
        int port = 1234;
        boolean listening = true;
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            while(listening)
            {
                new MyServerMultiThread(serverSocket.accept()).start();
                addClient();
            }
            
            
        }catch (IOException e)
        {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
    
    private void addClient()
    {
    
        clientNo++;
        clients.add(new Client("Client", clientNo++));
        //fire event here
        //get last joined
        initiater.newUserJoined(clients.get(clients.size()-1));
    }
    
}
