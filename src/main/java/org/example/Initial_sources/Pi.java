package org.example.Initial_sources;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Approximates PI using the Monte Carlo method.  Demonstrates
 * use of Callables, Futures, and thread pools.
 */
public class Pi {
    public static void main(String[] args) throws Exception {
        long total = 0;
        int[] n_fleche = {12000, 12000000, 120000000};
        int[] num_proc = {1, 2, 3, 4, 5, 6, 8, 10, 12};
        String filename = "D:\\IUT\\3emeAnneeIUT\\TP1_Mobile\\out_pi_salle_4c.txt";
        for (int fleche : n_fleche) {
            for (int proc : num_proc) {
                for (int j = 0; j < num_proc.length; j++) {
                    total = new Master().doRun(fleche / proc, proc, filename);
                }
            }
        }
        System.exit(0);
    }
}

/**
 * Creates workers to run the Monte Carlo simulation
 * and aggregates the results.
 */
class Master {
    public long doRun(int Ntot, int numWorkers, String fichier) throws InterruptedException, ExecutionException {

        long startTime = System.currentTimeMillis();

        // Create a collection of tasks
        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
        for (int i = 0; i < numWorkers; ++i) {
            tasks.add(new Worker(Ntot));
        }

        // Run them and receive a collection of Futures
        ExecutorService exec = Executors.newFixedThreadPool(numWorkers);
        List<Future<Long>> results = exec.invokeAll(tasks);
        long total = 0;

        // Assemble the results.
        for (Future<Long> f : results) {
            // Call to get() is an implicit barrier.  This will block
            // until result from corresponding worker is ready.
            total += f.get();
        }
        double pi = 4.0 * total / Ntot / numWorkers;

        long stopTime = System.currentTimeMillis();

        try {
            FileWriter fileWriter = new FileWriter(fichier, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.format("%e", (Math.abs((pi - Math.PI)) / Math.PI)) + " " + (Ntot * numWorkers) + " " + numWorkers + " " + (stopTime - startTime));

            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exec.shutdown();
        return total;
    }
}

/**
 * Task for running the Monte Carlo simulation.
 */
class Worker implements Callable<Long> {
    private int numIterations;

    public Worker(int num) {
        this.numIterations = num;
    }

    @Override
    public Long call() {
        long circleCount = 0;
        Random prng = new Random();
        for (int j = 0; j < numIterations; j++) {
            double x = prng.nextDouble();
            double y = prng.nextDouble();
            if ((x * x + y * y) < 1) ++circleCount;
        }
        return circleCount;
    }
}
