package com.company;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

public class MultiEchoServerNIO
{
    private static ServerSocketChannel serverSocketChannel;
    private static final int PORT = 1234;
    private static Selector selector;
    
    public void myRun()
    {
        ServerSocket serverSocket;
        System.out.println("Opening port…\n");
        try
        {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocket = serverSocketChannel.socket();

            InetSocketAddress netAddress = new InetSocketAddress(PORT);
            
            serverSocket.bind(netAddress);
            
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
            System.exit(1);
        }
        processConnections();
    }
    private static void processConnections()
    {
        do
        {
            try
            {
                int numKeys = selector.select();
                if (numKeys > 0)
                {
                    Set eventKeys = selector.selectedKeys();
                    Iterator keyCycler =eventKeys.iterator();
                    while (keyCycler.hasNext())
                    {
                        SelectionKey key = (SelectionKey)keyCycler.next();
                        
                        int keyOps = key.readyOps();
                        if ((keyOps & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT)
                        {
                            acceptConnection(key);
                            continue;
                        }
                        if ((keyOps & SelectionKey.OP_READ)== SelectionKey.OP_READ)
                        {
                            acceptData(key);
                        }
                        if((keyOps & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE)
                        {
                            writeDataToClient(key);
                        }
                    }
                }
            }
            catch(IOException ioEx)
            {
                ioEx.printStackTrace();
                System.exit(1);
            }
        }while (true);
    }
    private static void acceptConnection(SelectionKey key) throws IOException
    {
        
        SocketChannel socketChannel;
        Socket socket;
        socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socket = socketChannel.socket();
        System.out.println("Connection on "+ socket + ".");
        writeDataToClient(key);
        socketChannel.register(selector, SelectionKey.OP_READ);
        selector.selectedKeys().remove(key);
    }
    
    public static ByteBuffer stringToByteBuffer(String msg, Charset charset)
    {
        return ByteBuffer.wrap(msg.getBytes(charset));
    }
    
    private static void writeDataToClient(SelectionKey key) throws IOException
    {
        SocketChannel socketChannel;
        Socket socket;
        String myTestText = "You have joined the channel";
        socketChannel = (SocketChannel)key.channel();
        
        ByteBuffer buffer = stringToByteBuffer(myTestText,Charset.defaultCharset());
        
        socket = socketChannel.socket();
        socketChannel.register(selector,SelectionKey.OP_WRITE);
        try
        {
            buffer.flip();
            while (buffer.remaining()>0)
                socketChannel.write(buffer);
        }
        catch (IOException ioEx)
        {
            System.out.println("\nClosing socket "
                    + socket);
            closeSocket(socket);
        }
    }
    
    private static void acceptData(SelectionKey key) throws IOException
    {
        SocketChannel socketChannel;
        Socket socket;
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        
        socketChannel = (SocketChannel)key.channel();
        buffer.clear();
        int numBytes = socketChannel.read(buffer);
        System.out.println(numBytes + " bytes read.");
        socket = socketChannel.socket();
        
        
        if (numBytes==-1){
            key.cancel();
            System.out.println("\nClosing socket " + socket + "…");
            closeSocket(socket);
        }
        else
        {
            try
            {
                buffer.flip();
                while (buffer.remaining()>0)
                    socketChannel.write(buffer);
            }
            catch (IOException ioEx)
            {
                System.out.println("\nClosing socket "
                        + socket + "…");
                closeSocket(socket);
            }
        }
        selector.selectedKeys().remove(key);
    }
    private static void closeSocket(Socket socket)
    {
        try
        {
            if (socket != null)
                socket.close();
        }
        catch (IOException ioEx)
        {
            System.out.println(
                    "*** Unable to close socket! ***");
        }
    }
}