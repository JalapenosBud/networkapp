package com.company.MyServerStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServerMultiThread extends Thread implements BroadcastListener {
    
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    int port = 1234;
    static int clientNo = 0;
    Client tmpClient = null;
    String fromUser = "";
    
    public MyServerMultiThread(Socket socket)
    {
        super("MySuperServer");
        this.clientSocket = socket;
    }
    
    public void run()
    {
        try{
            // = new ServerSocket(port);
            System.out.println("listening for connections...");
            //clientSocket = serverSocket.accept();
            System.out.println("Got a connection...");
            //should make client to number 1 once connected
            clientNo++;
            //add new connected client to list
            ClientListSingleton.getInstance().clients.add(new Client("Client",clientNo));
            tmpClient = ClientListSingleton.getInstance().clients.get(ClientListSingleton.getInstance().clients.size()-1);
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            System.out.println("Awaiting message...");
            
            while(true)
            {
                fromUser = in.readLine();
                if( fromUser != null)
                {
                    System.out.println(tmpClient + ": "+ fromUser);
                    
                    //print what we get sent from client
                    out.println(fromUser);
                }
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void broadcastNewJoined(Client client) {
    
    }
}
