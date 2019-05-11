import edu.princeton.cs.algs4.QuickUnionUF;

import java.util.Arrays;

public class Percolation {

   private int[] id;
   private int[][] grid;

   private QuickUnionUF uf;

   // create n-by-n id, with all sites blocked
   public Percolation(int n) {
       if (n <= 0) {
           throw new IllegalArgumentException("n must be greater than zero.");
       }

       id = new int[n * n];
       grid = new int[n][n];

       uf = new QuickUnionUF(n * n + 2);
       //
       // for (int i = 0; i < n * n; i++) {
       //     id[i] = n;
       // }
   }

   // open site (row, col) if it is not open already (perform union)
   public void open(int row, int col) {
       validateDimensions(row, col);
       grid[row][col] = 1;
       int p = row * grid.length + col + 1;

       // edge cases
       // leftmost column, rightmost column

       // connect with space above if open
       if (row != 0 && isOpen(row - 1, col)) {
           int q = (row - 1) * grid.length + col + 1;
           uf.union(p, q);
       }

       // connect with space below if open
       if (row != grid.length - 1 && isOpen(row + 1, col)) {
           int q = (row + 1) * grid.length + col + 1;
           uf.union(p, q);
       }


       //
       // if (col != 0) {
       //     grid[row][col - 1] = 1;
       // }
       //
       // if (col != id.length - 1) {
       //     grid[row][col + 1] = 1;
       // }
   }

   // is site (row, col) open?
   public boolean isOpen(int row, int col) {
       validateDimensions(row, col);
       return grid[row][col] == 1;
   }

   // is site (row, col) full? TODO is this what full really means?
   public boolean isFull(int row, int col) {
       validateDimensions(row, col);
       return grid[row][col] == 0;
   }

   // number of open sites TODO performance
   public int numberOfOpenSites() {
       int cumSum = 0;

       for (int i = 0; i < id.length; i++) {
           for (int j = 0; j < id.length; j++) {
               cumSum += grid[i][j];
           }
       }
       return cumSum;
   }

   // does the system percolate?
   public boolean percolates() {
      return false;
   }

   // todo
   private void validateDimensions(int row, int col) {
        if (Math.min(row, col) < 0 || Math.max(row, col) >= grid.length) {
            throw new IllegalArgumentException("One of your dimensions is "
                + "outside of the prescribed bounds");
        }
   }

   public String toString() {
       String result = "";
       for (int i = 0; i < grid.length; i++) {
           result += Arrays.toString(grid[i]) + "\n";
       }
       return result;

   }

   // test client (optional)
   public static void main(String[] args) {
      Percolation perc = new Percolation(3);
      System.out.println(perc);

      // TODO 1, 1 is upper left
       perc.open(0, 1);
      perc.open(1,1);
      System.out.println(perc);
      System.out.println("---------------");

      System.out.println(perc.uf.count());
      // System.out.println(perc.numberOfOpenSites());
   }
}


/*
Performance requirements.  The constructor should take time proportional to n2;
all methods should take constant time plus a constant number of calls to the
union–find methods union(), find(), connected(), and count().
 */