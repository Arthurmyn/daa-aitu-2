package algorithm;

import metrics.PerformanceTracker;

public class ShellSort {
    public static void sort(int[] arr, PerformanceTracker tracker) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        long startTime = System.nanoTime();

        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1;
        }

        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                tracker.countSwapOrAllocation();
                int j = i;

                while (j >= gap) {
                    tracker.countComparison();

                    if (arr[j - gap] > temp) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                        tracker.countSwapOrAllocation();
                    } else {
                        break;
                    }
                }

                arr[j] = temp;
                tracker.countSwapOrAllocation();
            }
            gap /= 3;
        }

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        try {
            tracker.exportToCSV(elapsedTime / 1_000_000, "ShellSort_Knuth");
        } catch (Exception e) {
            System.err.println("Error writing metrics to CSV: " + e.getMessage());
        }
    }

    public static void printArray(int[] arr) {
        for (int value : arr) System.out.print(value + " ");
        System.out.println();
    }
}