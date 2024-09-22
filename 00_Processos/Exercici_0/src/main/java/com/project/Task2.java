package com.project;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class Task2 implements Runnable {

    private String taskInfo;

    public Task2() {

    }

    @Override
    public void run() {
        try {
            System.out.println("Task2: New user registered.");

            // Espera i segon missatge
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 4));
            System.out.println("Task2: User logged correctly.");

            // Espera i tercer missatge
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 4));
            System.out.println("Task2: User password changed.");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
