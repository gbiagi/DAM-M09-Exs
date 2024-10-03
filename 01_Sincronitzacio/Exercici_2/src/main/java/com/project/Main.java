package com.project;

import java.util.concurrent.*;
import java.util.concurrent.Semaphore;

public class Main {

    private static final Semaphore parkingSemaphore = new Semaphore(3);

    public static void main(String[] args) {
        // Crear los hilos
        ExecutorService executor = Executors.newFixedThreadPool(8);

        // Crear los procesos
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            Runnable process = () -> {
                ParkingLot parkingLot = new ParkingLot(parkingSemaphore, "Coche" + finalI);
                parkingLot.start();
            };
            // Ejecutar los procesos
            executor.execute(process);
        }

        // Cerrar el executor
        executor.shutdown();
        }
    }
