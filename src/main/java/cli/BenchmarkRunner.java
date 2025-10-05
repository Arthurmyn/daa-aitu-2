package cli;

import algorithm.ShellSort;
import metrics.PerformanceTracker;

import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes = (args.length > 0)
                ? Arrays.stream(args[0].split(",")).mapToInt(Integer::parseInt).toArray()
                : new int[]{10, 100, 1000, 10_000};

        Random rnd = new Random(42);

        for (int n : sizes) {
            int[] a = rnd.ints(n, -1_000_000, 1_000_000).toArray();
            PerformanceTracker t = new PerformanceTracker();

            long start = System.nanoTime();
            ShellSort.sort(a, t);              // твоя реализация с Knuth
            long timeMs = (System.nanoTime() - start) / 1_000_000;

            // вывод + запись метрик
            System.out.printf("n=%d | time=%d ms | cmp=%d | ops=%d%n",
                    n, timeMs, t.getComparisons(), t.getSwapsOrAllocations());
            try {
                t.exportToCSV(timeMs, "ShellSort_CLI_n_" + n);
            } catch (Exception e) {
                System.err.println("CSV error: " + e.getMessage());
            }
        }
    }
}