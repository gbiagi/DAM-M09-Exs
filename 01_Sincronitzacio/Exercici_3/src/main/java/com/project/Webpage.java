package com.project;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Webpage extends Thread{
    private final Semaphore semaphore;
    private String name;

    public Webpage(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(name + " waiting connection...");
            semaphore.acquire(); // Adquirir el semaforo
            System.out.println(name + " connected.");

            // Simular el tiempo de conexion
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 20000));

            System.out.println(name + " disconnected.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
