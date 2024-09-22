package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
	public static void main(String[] args) {

		// Crear un executor amb un pool de 3 fils
		ExecutorService executor = Executors.newFixedThreadPool(2);

		// Llista per emmagatzemar les tasques
		List<Runnable> tasks = new ArrayList<>();

		// Crear les tasques
		tasks.add(new Task1());
		tasks.add(new Task2());

		// Executar les tasques en parallel
		for (Runnable task : tasks) {
			executor.execute(task);
		}

		// Tancar l'executor
		executor.shutdown();
	}
}