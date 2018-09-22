package com.company;

import java.io.IOException;
import java.net.SocketException;

public class networkappserver {
    public static void main (String[] args) throws Exception {
        UDPServer server = new UDPServer();
        server.run();
    }
}
