package com.company.MoreEventsMrMood;

public class ADayInTheLife {
    public static void main(String[] args)
    {
        MrHappyObject happy = new MrHappyObject();
        MoodListener sky = new Sky();
        MoodListener birds = new FlockOfBirds();

        happy.addMoodListener(sky);
        happy.addMoodListener(birds);

        System.out.println("let's pinch Mr. Happy and see what happens!");
        happy.receivePinch();

        System.out.println("");
        System.out.println("Let's hug Mr. Happy and see what happens");
        happy.receiveHug();

        System.out.println("");
        System.out.println("make him angry!");
        System.out.println("");
        System.out.println("one pinch.. hehe");
        happy.receivePinch();
        System.out.println("");
        System.out.println("second pinch, look out");
        happy.receivePinch();
    }
}
