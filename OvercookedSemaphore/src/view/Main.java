package view;

import controller.OvercookedSemaphore;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main (String[] args)
    {
        Semaphore semaphore = new Semaphore(1);

        for (int x = 1; x < 6; x++)
        {
            OvercookedSemaphore cook = new OvercookedSemaphore(x, semaphore);
            cook.start();
        }
    }
}
