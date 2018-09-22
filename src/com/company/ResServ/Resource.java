package com.company.ResServ;

class Resource
{
    private int numResources;
    private final int MAX = 5;
    public Resource(int startLevel)
    {
        numResources = startLevel;
    }
    public int getLevel()
    {
        return numResources;
    }
    public synchronized int addOne()
    {
        try
        {
            while (numResources >= MAX)
                wait();
            numResources++;
//'Wake up' any waiting consumer…
            notifyAll();
        }
        catch (InterruptedException interruptEx)
        {
            System.out.println(interruptEx);
        }
        return numResources;
    }
    public synchronized int takeOne()
    {
        try
        {
            while (numResources == 0)wait();
            numResources--;
//'Wake up' waiting producer…
            notify();
        }
        catch (InterruptedException interruptEx)
        {
            System.out.println(interruptEx);
        }
        return numResources;
    }
}