/*
 * FunctionEvaluator.java -- Statistical analyzer for execution time measurement
 *
 * Assumes that there are a class who inherits FunctionEvaluator class and thus implements onerun()
 * method which measures the execution time of some operation and returns that measured time. Like
 * public class FunctionEvaluatorTester extends FunctionEvaluator {
 *   public double onerun() {
 *       long tick = System.nanoTime();
 *       try {
 *           Thread.sleep(100);
 *       } catch(Exception e) {
 *       }
 *       long tack = System.nanoTime();
 *
 *       return (double)(tack-tick)/1e6; // convert ns to ms
 *   }
 *
 *   public static void main(String[] args) {
 *       FunctionEvaluatorTester t = new FunctionEvaluatorTester();
 *
 *       System.out.println("Execution time was " + t + "ms");
 *   }
 *}
 *
 * When you call measure() method, it will run onerun() for multiple time (to improve measurement accuracy)
 * and calculates the mean and stadard variance of those measurements.
 *
 * Copyright (C) 2017 by ZyMIX Oy. All rights reserved.
 * Author(s): Jarkko Vuori
 * Modification(s):
 *   First version created on 25.03.2017
 */
import java.util.*;
import javafx.util.*;

public abstract class FunctionEvaluator {
    private static final int N = 300;

    private ArrayList<Double> running_times;

    FunctionEvaluator() {
        running_times = new ArrayList<Double>();
    }

    /**
     * Actual execution time measurement is done here
     * <p>
     * This method is called multiple time. Be careful what is the portion of the method whose
     * execution time should be measured. Use System.nanoTime() for accurate time measurement.
     *
     * @return Returns the measured execution time
     */
    public abstract double onerun();

    /**
     * Measurement starts here
     * <p>
     * This method calls onerun() multiple time and removes outliers and does the statistical
     * analysis of those results.
     *
     * @return Returns a pair whose key component contains mean of execution time measurements and value
     *         component contains standard deviation of those measurements.
     */
    public Pair<Double,Double> measure() {
        double mean, stdev;
        running_times.clear();

        // take the measurements
        int priority = Thread.currentThread().getPriority();
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        onerun();	// warm-up cache
        for (int k = 0; k < N; k++) {
            running_times.add(onerun());
            Thread.yield();	// give other processes time also
        }
        Thread.currentThread().setPriority(priority);

        // remove some largest values as an outliers (errors tend to only increase the measured time)
        Collections.sort(running_times,  Collections.reverseOrder());
        for (int k = 0; k < 3*N/4; k++)
            running_times.remove(0);

        // calculate mean
        mean = 0.0;
        for (double v : running_times)
            mean = mean + v;
        mean = mean / running_times.size();

        // calculate variance
        stdev = 0.0;
        for(double v : running_times) {
            stdev += (v-mean)*(v-mean);
        }
        stdev = Math.sqrt(stdev/(running_times.size()-1));

        return new Pair<>(mean, stdev);
    }

    @Override
    public String toString() {
        Pair <Double, Double> result = measure();

        StringBuilder sb = new StringBuilder();

        Formatter formatter = new Formatter(sb);
        formatter.format("%f+-%f", result.getKey(), result.getValue()*3);  // 99% confidence interval, three times standard deviation

        return sb.toString();
    }
}
