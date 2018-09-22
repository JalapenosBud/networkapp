package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPEchoServer {
    public static ServerSocket serverSocket;
    public static final int PORT = 1234;

    public void thisIsTheServer()
    {
        System.out.println("opening port...\n");
        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException ioEx)
        {
            System.out.printf("can't attach port");
            System.exit(1);
        }
        do {
            handleClient();
        }while(true);

    }

    private void handleClient()
    {
        Socket link = null;

        try
        {
            //accept incoming connections
            //and return a socket object
            link = serverSocket.accept();



            //scanner makes sure we get all string input validated
            Scanner input = new Scanner(link.getInputStream());

            //here we make a printwriter stream that uses the data from the socket above
            //and then we flush, ie reset data for every call of println so we empty the buffer
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);

            int numMessages = 0;

            //this is the input we wnt to send
            String message = input.nextLine();

            //use printwriter object for getting and printing data from socket
            while(!message.equals("***CLOSE***"))
            {
                System.out.println("Message received.");
                numMessages++;
                output.println("message " + numMessages + ": " + message);

                message = input.nextLine();
            }

            output.println(numMessages + " messages received.");
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }

        finally {
            try
            {
                System.out.println("\n* Closing connection... *");
                link.close();
            }
            catch (IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
