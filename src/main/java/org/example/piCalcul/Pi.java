package org.example.piCalcul;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        int[] n_fleche = {1200000, 12000000, 120000000};
        int n_total_faible = 25000000; // 15000000, 25000000
        int[] num_proc = {1, 2, 3, 4, 5, 6, 8, 10};
        String filename = "out_pi_Scala_Forte_salle_G26_4c.txt"; // out_pi_Scala_Faible_salle_G26_4c
        boolean scalabilite_forte = true;

        if (scalabilite_forte) {
            // Scalabilité forte
            for (int f : n_fleche) {
                for (int n : num_proc) {
                    for (int j = 0; j < 10; j++) {
                        total = new Master().doRun(f / n, n, filename);
                    }
                }
            }
        } else {
            // scalabilite faible
            for (int n : num_proc) {
                for (int j = 0; j < 10; j++) {
                    total = new Master().doRun(n_total_faible, n, filename);
                }
            }
        }
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
