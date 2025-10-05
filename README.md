# Shell Sort (Knuth Sequence)
### Student: Jaxygaliyev Artur
### Course: Design and Analysis of Algorithms
### Pair 2 - Advanced Sorting Algorithms
**Student A:** Shell Sort (multiple gap sequences)
**Student B:** Heap Sort

##  Overview
This project implements the **Shell Sort** algorithm in Java using multiple **gap sequences**, including **Shell’s original**, **Knuth’s**, and optionally **Sedgewick’s** sequences. The project is structured as a Maven module and includes:
- Source code implementation (`ShellSort.java`)
- Performance tracking (`PerformanceTracker.java`)
- CLI benchmark runner (`BenchmarkRunner.java`)
- Comprehensive JUnit tests (`ShellSortTest.java`)
- Automatically generated CSV metrics for empirical analysis

##  Algorithm Description
Shell Sort is an **optimization of Insertion Sort** that allows the exchange of items far apart. It starts with a large gap between elements, performing a “gapped” insertion sort, and gradually reduces the gap until it becomes 1 — where the algorithm behaves as standard insertion sort.

### Knuth Gap Sequence
Knuth’s sequence is defined as:

h = 3 * h + 1   (starting from h = 1)

This yields gaps like: **1, 4, 13, 40, 121, 364, …**
Knuth’s sequence provides a good balance between performance and implementation simplicity.

##  Complexity Analysis
| Case | Time Complexity | Space Complexity | Notes |
|------|------------------|------------------|-------|
| **Best** | O(n log n) | O(1) | Nearly sorted arrays |
| **Average** | O(n^1.25) | O(1) | For typical random data |
| **Worst** | O(n²) | O(1) | For reverse-sorted arrays |
| **Stable?** | ❌ No | - | Stability not guaranteed |

##  Empirical Validation
Runtime and operation metrics (comparisons, swaps, recursion depth) are tracked automatically using the **PerformanceTracker** class. Data is saved to `target/metrics.csv`.

Example output:

algorithm,time_ms,comparisons,allocations,maxDepth
ShellSort_Knuth,0,706,1089,0
ShellSort_Knuth,1,13154,19049,0
ShellSort_Knuth,5,229514,309090,0

These values confirm **sub-quadratic behavior**, consistent with the expected O(n^1.25–1.5).

##  How to Run
###  In IntelliJ IDEA
1. Open the project.
2. Run the class: `cli.BenchmarkRunner`.
3. Results will appear in the console and in `target/metrics.csv`.

###  Using Maven (Terminal)
```bash
# Run benchmark with default sizes
mvn -q exec:java -Dexec.mainClass=cli.BenchmarkRunner

# Run with custom input sizes
mvn -q exec:java -Dexec.mainClass=cli.BenchmarkRunner -Dexec.args="10,100,1000,10000"

 Run Unit Tests

mvn test

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

Input Size (n)	Time (ms)	Comparisons	Swaps	Expected Trend
100	0	706	1089	~O(n log n)
1,000	1	13,154	19,049	~O(n^1.3)
10,000	5	229,514	309,090	~O(n^1.4)

 Conclusions
	•	Knuth’s gap sequence provides significantly fewer comparisons than Shell’s original sequence.
	•	Empirical results confirm sub-quadratic growth in runtime.
	•	Shell Sort remains simple, memory-efficient, and adaptive for moderately sized datasets.
	•	Best suited when array size is medium and extra memory is limited.


Commit Storyline Summary

Commit	Description
init: maven project structure, junit5, ci setup	Project initialization
feat(metrics): add PerformanceTracker for comparisons, swaps, recursion depth	Metrics tracking added
feat(algorithm): baseline Shell Sort implementation	Basic version
feat(algorithm): implement Knuth gap sequence	Optimized version
test(algorithm): add edge case tests	Unit tests added
feat(cli): benchmark runner with configurable input sizes	Benchmarking CLI
docs(readme): usage instructions and complexity analysis	Documentation added
