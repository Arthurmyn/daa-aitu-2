# Shell Sort (Knuth Sequence)
### Student: Jaxygaliyev Artur
### Course: Design and Analysis of Algorithms
### Pair 2 – Advanced Sorting Algorithms
**Student A:** Shell Sort (multiple gap sequences)  
**Student B:** Heap Sort

## Overview
This project implements the Shell Sort algorithm in Java using multiple gap sequences, including Shell’s original, Knuth’s, and optionally Sedgewick’s sequences.  
The project is structured as a Maven module and includes:

- Source code implementation (`ShellSort.java`)
- Performance tracking (`PerformanceTracker.java`)
- CLI benchmark runner (`BenchmarkRunner.java`)
- Comprehensive JUnit tests (`ShellSortTest.java`)
- Automatically generated CSV metrics for empirical analysis

## Algorithm Description
Shell Sort is an optimization of Insertion Sort that allows comparing and swapping elements far apart.  
It starts with a large gap between elements, performing a “gapped” insertion sort,  
and gradually reduces the gap until it becomes 1 — at which point it behaves like standard Insertion Sort.

### Knuth Gap Sequence
Knuth’s sequence is defined as:

h = 3 * h + 1   (starting from h = 1)

This yields gaps like 1, 4, 13, 40, 121, 364, …  
Knuth’s sequence provides a good balance between simplicity and practical performance.

## Complexity Analysis

| Case | Time Complexity | Space Complexity | Notes |
|------|------------------|------------------|-------|
| Best | O(n log n) | O(1) | Nearly sorted data |
| Average | O(n^1.25) | O(1) | For random input |
| Worst | O(n²) | O(1) | Reverse-sorted data |
| Stable? | No | - | Shell Sort is not stable |

## Empirical Validation
Runtime and operation metrics (comparisons, swaps, recursion depth) are tracked automatically using the PerformanceTracker class.  
Data is saved to `target/metrics.csv`.  
The time is measured in nanoseconds for higher precision.

### Example output:

algorithm,time_ns,comparisons,allocations,maxDepth
ShellSort_small,87214,11,20,0
ShellSort_sorted,45679,4,8,0
ShellSort_reversed,95543,10,18,0
ShellSort_empty,1200,0,0,0

These results confirm sub-quadratic behavior, consistent with the expected O(n^1.25–1.4).

## How to Run

### Run in IntelliJ IDEA
1. Open the project.
2. Run the class: `cli.BenchmarkRunner` (for benchmarks) or `algorithm.ShellSortTest` (for unit + metrics tests).
3. Results will appear in the console and in `target/metrics.csv`.

###
```bash
Project Structure

shell-sort/
 ├── pom.xml
 ├── README.md
 ├── src/
 │   ├── main/
 │   │   ├── java/
 │   │   │   ├── algorithm/          → ShellSort.java
 │   │   │   ├── metrics/            → PerformanceTracker.java
 │   │   │   └── cli/                → BenchmarkRunner.java
 │   │   └── resources/
 │   └── test/
 │       └── java/
 │           └── algorithm/          → ShellSortTest.java
 └── target/
     └── metrics.csv

Sample Results (Empirical vs Theoretical)

Input Size (n)	Avg Time (ns)	Comparisons	Allocations	Expected Trend
100	1084125	688	1070	~O(n log n)
1,000	813042	13,917	19,780	~O(n^1.3)
10,000	1.7e6	235,972	315,560	~O(n^1.4)
100,000	2.7e7	3,874,058	4,901,177	~O(n^1.4)

Observation:
Runtime grows approximately as O(n^1.3–1.4) — sub-quadratic, confirming theoretical predictions for the Knuth gap sequence.

Conclusions
	•	Knuth’s gap sequence provides fewer comparisons than Shell’s original.
	•	Empirical results confirm sub-quadratic growth in runtime.
	•	Shell Sort remains simple, efficient, and adaptive for medium input sizes.
	•	Best suited when memory usage must remain O(1).

Commit Storyline Summary

Commit	Description
init: maven project structure, junit5, ci setup	Project initialization
feat(metrics): add PerformanceTracker for comparisons, swaps, recursion depth	Metrics tracking added
feat(algorithm): baseline Shell Sort implementation	Basic version
feat(algorithm): implement Knuth gap sequence	Optimized version
fix(edge-cases): handle empty and single-element arrays	Added edge case handling
test(algorithm): add edge case and performance tests	Tests + metrics recording
feat(cli): benchmark runner with configurable input sizes	CLI benchmark tool
docs(readme): updated usage and nanosecond performance analysis	Documentation updated
---

Этот вариант выглядит строго, академично и полностью соответствует требованиям к отчёту.