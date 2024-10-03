package com.project;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ParkingLot extends Thread {
    private final Semaphore semaphore;
    private String name;

    public ParkingLot(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(name + " esta buscando plaza de aparcamiento...");
            semaphore.acquire(); // Adquirir el semaforo
            System.out.println(name + " ha conseguido aparcar.");

            // Simular el tiempo que el coche esta aparcado
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 15000));

            System.out.println(name + " se va del aparcamiento.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
