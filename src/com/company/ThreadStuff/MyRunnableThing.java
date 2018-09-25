package com.company.ThreadStuff;

public class MyRunnableThing<T> implements Runnable {

    private T genericType;

    public MyRunnableThing(T genericType)
    {
        this.genericType = genericType;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {

            System.out.print(genericType);

        }

    }
}
