package com.project;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;

class Task3 implements Callable<Integer> {

    private ConcurrentHashMap<String, Integer> sharedData;

    public Task3(ConcurrentHashMap<String, Integer> sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public Integer call() throws InterruptedException {
        int delay = ThreadLocalRandom.current().nextInt(1, 4);
        TimeUnit.SECONDS.sleep(delay);
        Integer value = sharedData.get("transf1");
        if (value == null) {
            System.out.println("Saldo actual en la cuenta: 0€");
            return 0;
        } else {
            System.out.println("Saldo actual en la cuenta: " + value + "€");
            return value;
        }
    }
}