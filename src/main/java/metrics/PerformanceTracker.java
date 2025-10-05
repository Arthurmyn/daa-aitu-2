package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.LongAdder;

public class PerformanceTracker {
    private final LongAdder comparisons = new LongAdder();
    private final LongAdder swapsOrAllocations = new LongAdder();
    private final LongAdder activeRecursions = new LongAdder();
    private final LongAdder deepestRecursion = new LongAdder();

    public void countComparison() {
        comparisons.increment();
    }

    public void countSwapOrAllocation() {
        swapsOrAllocations.increment();
    }

    public void enterRecursion() {
        activeRecursions.increment();
        long currentDepth = activeRecursions.sum();
        if (currentDepth > deepestRecursion.sum()) {
            deepestRecursion.reset();
            deepestRecursion.add(currentDepth);
        }
    }

    public void exitRecursion() {
        activeRecursions.decrement();
    }

    public void resetAll() {
        comparisons.reset();
        swapsOrAllocations.reset();
        activeRecursions.reset();
        deepestRecursion.reset();
    }

    public void exportToCSV(long runtimeMillis, String algorithmName) throws IOException {
        try (FileWriter fw = new FileWriter("target/metrics.csv", true);
             PrintWriter out = new PrintWriter(fw)) {
            out.printf("%s,%d,%d,%d,%d%n",
                    algorithmName,
                    runtimeMillis,
                    comparisons.sum(),
                    swapsOrAllocations.sum(),
                    deepestRecursion.sum());
        }
    }

    public long getComparisons() { return comparisons.sum(); }
    public long getSwapsOrAllocations() { return swapsOrAllocations.sum(); }
    public long getDeepestRecursion() { return deepestRecursion.sum(); }
}