package com.company.ThreadStuff;

public class BalanceCheck implements Runnable {

    public static final double count = 1000;
    public static int balance = 0;

    @Override
    public void run() {
        synchronized (this)
        {
            for (int i = 0; i < 1000; i++) {
                balance++;

            }
        }

    }
}
