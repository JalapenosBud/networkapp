package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TheServer {

    //listen on port 1234 for incoming activity
    ServerSocket serverSocket = null;


    public void Connect() throws IOException {

        serverSocket = new ServerSocket(   1234);
        //accept incoming connections
        //and return a socket object
        Socket link = serverSocket.accept();

        //scanner makes sure we get all string input validated
        Scanner input = new Scanner(link.getInputStream());

        //here we make a printwriter stream that uses the data from the socket above
        //and then we flush, ie reset data for every call of println so we empty the buffer
        PrintWriter output = new PrintWriter(link.getOutputStream(),true);

        //use printwriter object for getting and printing data from socket
        output.println("awaiting data...");
        //this is the input we wnt to send
        String strInput = input.nextLine();

        link.close();
    }
}
