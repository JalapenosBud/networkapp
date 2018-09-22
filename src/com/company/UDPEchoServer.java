package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPEchoServer {

    private static final int PORT = 1234;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacker;
    private static byte[] buffer;

    public UDPEchoServer() throws SocketException {
    }

    public void thisDaServer() throws IOException {
        //need buffer array and the length of it
        //this is for incoming datagrams
        try
        {
            datagramSocket = new DatagramSocket(1234);
        }
        catch (SocketException sockEx)
        {
            System.out.println("Unble to open port!");
            System.exit(1);
        }

        handleClient();

    }

    private void handleClient() throws IOException {

        try
        {
            String messageIn, messageOut;
            int numMessages = 0;
            InetAddress clientAddress = null;
            int clientPort;

            do {
                buffer = new byte[256];
                inPacket = new DatagramPacket(buffer,buffer.length);

                //recieve a new packet with buffer variables and sizes
                datagramSocket.receive(inPacket);

                //get address from the packet received
                clientAddress = inPacket.getAddress();

                //get port from packet received
                clientPort = inPacket.getPort();

                messageIn = new String(inPacket.getData(),0,inPacket.getLength());

                System.out.println("Message received");
                numMessages++;
                messageOut = "Message " + numMessages + ": " + messageIn;

                outPacker = new DatagramPacket(messageOut.getBytes(),messageOut.length(),clientAddress,clientPort);
                datagramSocket.send(outPacker);
            }while(true);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally {
            System.out.println("\n* Closing connection... *");
            datagramSocket.close();
        }
    }
}
