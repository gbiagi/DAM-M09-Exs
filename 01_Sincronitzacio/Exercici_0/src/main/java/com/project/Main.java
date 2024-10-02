package com.project;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        
        // Crear una frase que se completara con cada hilo
        StringBuilder fraseCompleta = new StringBuilder();

        // Crear el CyclicBarrier
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println(fraseCompleta + " Toodos los procesos completados.");
            }
        });

        // Crear los hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Tareas para simular procesos
        Runnable process1 = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("1. Primer proceso completado");
                fraseCompleta.append("Uno... ");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable process2 = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("2. Segundo proceso completado");
                fraseCompleta.append("Dos... ");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable process3 = () -> {
            try {
                Thread.sleep(3000);
                System.out.println("3. Tercer proceso completado");
                fraseCompleta.append("Tres... ");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Ejecutar las tareas
        executor.execute(process1);
        executor.execute(process2);
        executor.execute(process3);

        // Cerrar el executor
        executor.shutdown();
    }
}
