package com.company.MyServerStuff;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
    
    public static ArrayList<Socket> clientSockets = new ArrayList<>();
    private PrintWriter out = null;
    public MyServer()
    {
        
        int port = 1234;
        boolean listening = true;
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            while(listening)
            {
                new MyServerMultiThread(serverSocket.accept()).start();
                if(clientSockets.size() > 0)
                {
                    broadcastToAllClients();
                }
            }
            
            
        }catch (IOException e)
        {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
    
    public void broadcastToAllClients() throws IOException {
        PrintWriter out = null;
    
        for (int i = 0; i < clientSockets.size(); i++) {
            out = new PrintWriter(clientSockets.get(i).getOutputStream(),true);
            out.println("you joined...");
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
