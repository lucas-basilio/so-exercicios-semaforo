package view;

import controller.SemaphoreRequests;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args)
    {
        Semaphore semaphore = new Semaphore(1);

        for (int x = 1; x < 22; x++)
        {
            SemaphoreRequests req = new SemaphoreRequests(x, semaphore);
            req.start();
        }
    }
}
