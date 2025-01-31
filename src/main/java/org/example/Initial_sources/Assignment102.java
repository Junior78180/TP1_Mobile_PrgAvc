// Estimate the value of Pi using Monte-Carlo Method, using parallel program
package org.example.Initial_sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class PiMonteCarlo {
    AtomicInteger nAtomSuccess;
    int nTot;
    double value;

    int nb_proc;

    class MonteCarlo implements Runnable {
        @Override
        public void run() {
            double x = Math.random();
            double y = Math.random();
            if (x * x + y * y <= 1)
                nAtomSuccess.incrementAndGet();
        }
    }

    public PiMonteCarlo(int nTot, int nb_proc) {
        this.nAtomSuccess = new AtomicInteger(0);
        this.nTot = nTot;
        this.value = 0;

        this.nb_proc = nb_proc;
    }

    public double getPi() {
        ExecutorService executor = Executors.newWorkStealingPool(nb_proc);
        for (int i = 1; i <= nTot; i++) {
            Runnable worker = new MonteCarlo();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) { // Tant que executor n'est pas fini
        }
        value = 4.0 * nAtomSuccess.get() / nTot;
        return value;
    }
}

public class Assignment102 {
    public static void main(String[] args) {

        int[] n_fleche = {12000, 12000000, 1200};
        int[] num_proc = {1, 2, 4, 6, 8, 10, 12, 16};
        for (int fleche : n_fleche) {
            for (int n_proc : num_proc) {
                PiMonteCarlo PiVal = new PiMonteCarlo(fleche / n_proc, n_proc);
                long startTime = System.currentTimeMillis();
                double value = PiVal.getPi();
                long stopTime = System.currentTimeMillis();
                double relativeError = Math.abs((value - Math.PI)) / Math.PI;
                long timeDuration = stopTime - startTime;

                try {
                    File fichier = new File("D:\\IUT\\3emeAnneeIUT\\TP1_Mobile\\out_ass102_salle4c.txt");
                    FileOutputStream fos = new FileOutputStream(fichier, true);
                    PrintStream ps = new PrintStream(fos);
                    System.setOut(ps);
                    System.out.printf("%e %d %d %d%n", relativeError, PiVal.nTot, PiVal.nb_proc, timeDuration);
                    ps.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        System.exit(0);
    }
}