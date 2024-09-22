package com.project;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class Task1 implements Runnable {

    public Task1() {}

    @Override
    public void run() {
        try {
            System.out.println("Task1: Checking server status...");

            // Espera i segon missatge
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 4));
            System.out.println("Task1: Server status: OK.");

            // Espera i tercer missatge
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 4));
            System.out.println("Task1: Check over.");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
