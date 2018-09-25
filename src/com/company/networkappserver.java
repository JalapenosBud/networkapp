package com.company;

import com.company.NIO.MultiEchoServerNIO;
import com.company.ThreadStuff.BalanceCheck;
import com.company.ThreadStuff.MyRunnableThing;

import java.util.ArrayList;
import java.util.Scanner;

public class networkappserver {
    public static final double count = 1000;

    public static void main (String[] args) {

        /*int number = 1;

        MyRunnableThing a = new MyRunnableThing("a");
        MyRunnableThing b = new MyRunnableThing("b");
        MyRunnableThing num = new MyRunnableThing(number);

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(num);

        t1.start();
        t2.start();
        t3.start();*/

        BalanceCheck balanceCheck = new BalanceCheck();

        ArrayList<Thread> tenThreads = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            tenThreads.add(new Thread(balanceCheck));
        }

        for (Thread thread: tenThreads) {
            thread.start();
        }
        System.out.println(BalanceCheck.balance);

        double finalPercentage = (BalanceCheck.balance / (count * BalanceCheck.count) *100);

        System.out.println("percentage is: " + finalPercentage);

        //MultiEchoServerNIO server = new MultiEchoServerNIO();
        //server.myRun();
        //ResourceServer server = new ResourceServer();
        //server.myRun();
    }
}
