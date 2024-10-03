package com.project;

import java.util.concurrent.*;

public class Main {
    private static final Semaphore webSemaphore = new Semaphore(4);

    public static void main(String[] args) {
        // Crear los hilos
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Crear los procesos
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable process = () -> {
                Webpage webpage = new Webpage(webSemaphore, "User00" + finalI);
                webpage.start();
            };
            // Ejecutar los procesos
            executor.execute(process);
        }

        // Cerrar el executor
        executor.shutdown();
    }
}
