/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)  {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

    /**
     * Takes two command-line arguments n and T, performs T independent
     * computational experiments on an n-by-n grid, and prints
     * the sample mean, sample standard deviation, and the 95% confidence
     * interval for the percolation threshold.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.print("What size grid?: ");
        int n = StdIn.readInt();

        System.out.print("How many experiments? ");
        int[] runs = new int[StdIn.readInt()];

        for (int i = runs.length; i > 0; i--) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int randomRow = (int) Math.floor(StdRandom.uniform() * n);
                int randomCol = (int) Math.floor(StdRandom.uniform() * n);

                perc.open(randomRow, randomCol);
            }

            runs[i - 1] = perc.numberOfOpenSites();
        }

        double mean = StdStats.mean(runs) / (n * n);
        double stddev = StdStats.stddev(runs) / (n * n);

        System.out.println("mean:                      " + mean);
        System.out.println("stddev:                    " + stddev);
        System.out.println("95% confidence interval:   [" + (mean - stddev * 2.5)
                            + ", " + (mean + stddev * 2.5) + "]");

    }
}
