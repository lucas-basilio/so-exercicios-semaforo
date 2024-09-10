package controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class OvercookedSemaphore extends Thread{

    private Semaphore semaphore;
    private int TID;
    private String food;

    public OvercookedSemaphore (int tid, Semaphore semaphore)
    {
        this.TID = tid;
        this.semaphore = semaphore;
        this.food = this.TID % 2 == 0 ? ("Lasanha a Bolonhesa " + this.TID) : ("Sopa de Cebola " + this.TID);
    }

    private void cooking ()
    {
        boolean isEven = this.TID % 2 == 0;
        //long timing = (long)((Math.random() * (isEven ? (1.2 - 0.6) : (0.8 - 0.5))) * 1000);
        double timing = (Math.random() + (isEven ? (1.2 - 0.6) : (0.8 - 0.5)));

        System.out.println("Uma " + this.food + " está sendo preparada...");

        try
        {
            int percentage = (int)(timing / 0.1d);
            int count = 1;
            for (int x = 0; x < percentage; x++)
            {
                sleep(100);
                System.out.println("A " + this.food + " está " + (percentage * count) + "% concluída...");
                count++;
            }
            System.out.println("A " + this.food + " está pronta!");
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    private void deliver ()
    {
        try
        {
            semaphore.acquire();
            System.out.println("A " + this.food + " está sendo entregue...");
            sleep(500);
            System.out.println("A " + this.food + " foi entregue!");
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

    @Override
    public void run ()
    {
        cooking();
        deliver();
    }
}
