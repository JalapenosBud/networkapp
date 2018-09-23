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
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            System.out.println("Awaiting message...");
            
            while(true)
            {
                fromUser = in.readLine();
                if( fromUser != null)
                {
                    System.out.println("Client" + ": "+ fromUser);
                    
                    //print what we get sent from client
                    out.println(fromUser);
                }
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
