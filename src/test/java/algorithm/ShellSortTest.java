package algorithm;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShellSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};

        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, tracker);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};

        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, tracker);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testReversedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};

        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, tracker);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};

        PerformanceTracker tracker = new PerformanceTracker();
        ShellSort.sort(arr, tracker);

        assertArrayEquals(expected, arr);
    }
}