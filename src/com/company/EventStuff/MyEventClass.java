package com.company.EventStuff;

public class MyEventClass extends java.util.EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyEventClass(Object source) {
        super(source);
    }
}
