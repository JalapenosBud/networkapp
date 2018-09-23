package com.company;

import com.company.MyServerStuff.MyServer;

public class networkappserver {
    public static void main (String[] args) throws Exception {
        MultiEchoServerNIO server = new MultiEchoServerNIO();
        server.myRun();
        //ResourceServer server = new ResourceServer();
        //server.myRun();
    }
}
