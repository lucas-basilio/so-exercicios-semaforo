package controller;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRequests extends Thread{
    private int TID;
    private Semaphore semaphore;
    private int repetition;

    public SemaphoreRequests(int tid, Semaphore semaphore)
    {
        this.TID = tid;
        this.semaphore = semaphore;
        this.repetition = this.TID % 3 == 1 ? 2 : 3;
    }

    private void calc()
    {
        long timing;

        if (this.TID % 3 == 1) // 1, 4, 7, 10, 13, 16, 19
        {
            timing = (long)((Math.random() * (1.0 - 0.2) + 0.2) * 1000);
        }
        else if (this.TID % 3 == 2) // 2, 5, 8, 11, 14, 17, 20
        {
           timing = (long)((Math.random() + 0.5) * 1000);
        }
        else // 3, 6, 9, 12, 15, 18, 21
        {
            timing = (long)((Math.random() + 1.0) * 1000);
        }

        try
        {
            System.out.println("Thread " + this.TID + " está realizando calculo...");
            sleep(timing);
            System.out.println("Thread " + this.TID + " finalizou o calculo!");
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    private void request()
    {
        long timing;

        if (this.TID % 3 == 1)
        {
            timing = (long)((Math.random() * (1.0 - 0.2) + 0.2) * 1000);
        }
        else if (this.TID % 3 == 2)
        {
            timing = (long)((Math.random() + 0.5) * 1000);
        }
        else
        {
            timing = (long)((Math.random() + 1.0) * 1000);
        }

        System.out.println("Thread " + this.TID + " está realizando transação...");

        try
        {
            sleep(timing);
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }

        System.out.println("Thread " + this.TID + " finalizou a transação!");
    }

    @Override
    public void run()
    {
        for (int x = 0; x < repetition; x++)
        {
            calc();

            try
            {
                semaphore.acquire();
                request();
            }
            catch (Exception ex)
            {
                System.err.println(ex.getMessage());
            }
            finally
            {
                semaphore.release();
            }
        }
    }
}
