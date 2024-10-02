package com.project;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            listaNumeros.add(ThreadLocalRandom.current().nextInt(1, 10));
        }
        System.out.println("Lista de números: " + listaNumeros);

        // Crear el CyclicBarrier
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println("Todos los procesos han terminado.");
            }
        });

        // Crear los hilos
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Tareas para los procesos
        Runnable process1 = () -> {
            // Suma de los numeros
            try {
                int suma = 0;
                for (Integer listaNumero : listaNumeros) {
                    suma += listaNumero;
                }
                barrier.await();
                System.out.println("La suma de los números es: " + suma);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable process2 = () -> {
            // Media de los numeros
            try {
                double media = 0;
                for (Integer listaNumero : listaNumeros) {
                    media += listaNumero;
                }
                media = media / listaNumeros.size();
                barrier.await();
                System.out.println("La media de los números es: " + media);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable process3 = () -> {
            // Desviacion estandar de los numeros
            try {
                double desviacion = 0;
                int mediaEst = 0;
                for (Integer listaNumero : listaNumeros) {
                    mediaEst += listaNumero;
                }
                mediaEst = mediaEst / listaNumeros.size();
                for (Integer listaNumero : listaNumeros) {
                    desviacion += Math.pow(listaNumero - mediaEst, 2);
                }
                desviacion = Math.sqrt(desviacion / listaNumeros.size());
                barrier.await();
                System.out.println("La desviación estándar de los números es: " + desviacion);
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
