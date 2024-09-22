package com.project;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> sharedData = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Task1: Dades inicials
        Runnable task1 = () -> {
            try {
                int delay = ThreadLocalRandom.current().nextInt(1, 3); // Genera un retard aleatori entre 1 i 3 segons
                TimeUnit.SECONDS.sleep(delay);
                sharedData.put("transf1", 1000);
                System.out.println("Transferencia recibida: Importe: 1000€");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Task2: Modifica dades
        Runnable task2 = () -> {
            try {
                int delay = ThreadLocalRandom.current().nextInt(1, 4);
                TimeUnit.SECONDS.sleep(delay);
                sharedData.computeIfPresent("transf1", (key, value) -> value + 200);
                System.out.println("Sumando bonificacion por intereses: 20%");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Task3: Llegir dades i printar en consola
        Callable<Integer> task3 = new Task3(sharedData);

        try {
            executor.submit(task1);
            executor.submit(task2);
            Integer result = executor.submit(task3).get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No se pudo obtener el saldo de la cuenta.");
            e.printStackTrace();
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

    }
}