package com.docencia.semaforo;

import java.util.concurrent.Semaphore;

public class ColorSemaforo implements Runnable{

    String color = "";
    private static final Semaphore semaphoreRed = new Semaphore(1);
    private static final Semaphore semaphoreOrange = new Semaphore(1);
    private static final Semaphore semaphoreGreen = new Semaphore(1);
    
    public ColorSemaforo(String color) {
        this.color = color;
    }

    @Override
    public void run() {

        
        System.out.println(color);
        
    }

}
