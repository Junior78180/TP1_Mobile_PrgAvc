package org.example.piCalcul;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Approximates PI using the Monte Carlo method.  Demonstrates
 * use of Callables, Futures, and thread pools.
 */
public class Pi {
    public static void main(String[] args) throws Exception {
        long total = 0;
        int[] n_fleche = {12000, 12000000, 120000000};
        int n_total_faible = 25000000; // 15000000, 25000000
        int[] num_proc = {1, 2, 3, 4, 5, 6, 8, 10, 12};
        String filename = "D:\\IUT\\3emeAnneeIUT\\TP1_Mobile\\out_piMws_salle_4c.txt";
        boolean scalabilite_forte = false;

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
