package com.company.MyServerStuff;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class MyServer {
    
    public MyServer()
    {
        
        int port = 1234;
        boolean listening = true;
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            while(listening)
            {
                new MyServerMultiThread(serverSocket.accept()).start();
                
            }
            
            
        }catch (IOException e)
        {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
    
    /*private void addClient()
    {
        clientNo++;
        clients.add(new Client("Client", clientNo));
        //fire event here
        //get last joined
        initiater.newUserJoined(clients.get(clients.size()-1));
    }*/
    
}
