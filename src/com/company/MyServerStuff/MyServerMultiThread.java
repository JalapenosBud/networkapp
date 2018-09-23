package com.company.MyServerStuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServerMultiThread extends Thread {
    
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
            //this should also add a listener to the initiator
            //so when a new client gets added
            ClientListSingleton.getInstance().addClient(new Client("Client",clientNo));
            
            tmpClient = ClientListSingleton.getInstance().getLastPersonAdded();
    
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            //broadcast new client is added
            
            
            
            System.out.println("Awaiting message...");
            
            while(true)
            {
    
                out.println(tmpClient);
                //set String fromUser to reading what comes from the input stream
                //ie we receive what gets sent over the network
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
}
