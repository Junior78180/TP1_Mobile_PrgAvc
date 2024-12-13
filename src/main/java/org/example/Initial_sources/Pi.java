package org.example.Initial_sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
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
public class Pi 
{
    public static void main(String[] args) throws Exception 
    {
	long total = 0;
	// 10 workers, 50000 iterations each
	total = new Master().doRun(50000, 10);
	// crée une instance de la classe Master et lance la simulation Monte-Carlo avec 50 000 itérations réparties sur 10 workers
	System.out.println("total from Master = " + total);
    }
}

/**
 * Creates workers to run the Monte Carlo simulation
 * and aggregates the results.
 */
class Master {
    public long doRun(int Ntot, int numWorkers) throws InterruptedException, ExecutionException
    {

	long startTime = System.currentTimeMillis();

	// Create a collection of tasks
	List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
	for (int i = 0; i < numWorkers; ++i)
	    {
		tasks.add(new Worker(Ntot));
	    }

	// Run them and receive a collection of Futures
	ExecutorService exec = Executors.newFixedThreadPool(numWorkers);
	List<Future<Long>> results = exec.invokeAll(tasks);
	long total = 0;

	// Assemble the results.
	for (Future<Long> f : results)
	    {
		// Call to get() is an implicit barrier.  This will block
		// until result from corresponding worker is ready.
		total += f.get();
	    }
	double pi = 4.0 * total / Ntot / numWorkers;

	long stopTime = System.currentTimeMillis();

//	System.out.println("Approximation value of pi: " + pi );
//	System.out.println("Absolute error :" + (pi - Math.PI));
//	System.out.println("Relativ Error " + (Math.abs((pi - Math.PI)) / Math.PI));
//	System.out.println("Ntot: " + Ntot*numWorkers);
//	System.out.println("Available processors: " + numWorkers);
//	System.out.println("Time Duration: " + (stopTime - startTime) + "ms");

		try {
			File fichier = new File("D:\\IUT\\3emeAnneeIUT\\TP1_Mobile\\out_pi_salle_4c.txt");
			FileOutputStream fos = new FileOutputStream(fichier, true);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
			System.out.println("Approximation value of pi: " + pi +
					"\nRelativ error: " + (Math.abs((pi - Math.PI)) / Math.PI) +
					"\nNtot: "+ Ntot +
					"\nAvailable processors: "+ numWorkers +
					"\nTime Duration: "+ (stopTime - startTime) + "ms\n------------------");
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.exit(0);
		return total;
    }
}

/**
 * Task for running the Monte Carlo simulation.
 */
class Worker implements Callable<Long> 
{   
    private int numIterations;
    public Worker(int num) 
	{ 
	    this.numIterations = num; 
	}

  @Override
      public Long call() 
      {
	  long circleCount = 0;
	  Random prng = new Random ();
	  for (int j = 0; j < numIterations; j++) 
	      {
		  double x = prng.nextDouble();
		  double y = prng.nextDouble();
		  if ((x * x + y * y) < 1)  ++circleCount;
	      }
	  return circleCount;
      }
}
