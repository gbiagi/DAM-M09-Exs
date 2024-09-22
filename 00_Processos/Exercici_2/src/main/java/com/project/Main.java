package com.project;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // Task1: comprovar dades de l'usuari
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Comprobant dades de l'usuari...");
            return "Comprobant dades de l'usuari...";
        });

        // Task2: modificar el resultat anterior
        CompletableFuture<String> task2 = task1.thenApply(result -> {

            return result + " Dades correctes.";
        });

        // Task3: final
        CompletableFuture<Void> task3 = task2.thenAccept(result -> {
            System.out.println("System: " + result + " Usuari connectat.");
        });
        
        task1.join();
        task2.join();
        task3.join();
    }
}
