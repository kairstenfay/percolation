import edu.princeton.cs.algs4.QuickUnionUF;

import java.util.Arrays;

public class Percolation {

   private int[][] grid;
   private int open;

   private QuickUnionUF uf;

   // create n-by-n id, with all sites blocked
   public Percolation(int n) {
       if (n <= 0) {
           throw new IllegalArgumentException("n must be greater than zero.");
       }
       grid = new int[n][n];
       uf = new QuickUnionUF(n * n + 2);
   }

   // open site (row, col) if it is not open already (perform union)
   public void open(int row, int col) {
       validate(row, col);
       if (grid[row][col] == 1) {
           return;
       }

       grid[row][col] = 1;
       open++;

       int p = row * grid.length + col + 1;
       
       if (row == 0) {  // connect with pseudo space at top
           uf.union(p, 0);
       } else if (isOpen(row - 1, col)) {  // connect with space above if open
           int q = (row - 1) * grid.length + col + 1;
           uf.union(p, q);
       }

       if (row == grid.length - 1) {  // connect with pseudo space at bottom
           uf.union(p, grid.length * grid.length + 1);
       } else if (isOpen(row + 1, col)) {  // connect with space below if open
           int q = (row + 1) * grid.length + col + 1;
           uf.union(p, q);
       }
       
       // connect with space to left if open
       if (col != 0 && isOpen(row, col - 1)) {
           int q = row * grid.length + (col - 1) + 1;
           uf.union(p, q);
       }

       // connect with space to right if open
       if (col != grid.length  - 1 && isOpen(row, col + 1)) {
           int q = row * grid.length + (col + 1) + 1;
           uf.union(p, q);
       }
   }

   // is site (row, col) open?
   public boolean isOpen(int row, int col) {
       validate(row, col);
       return grid[row][col] == 1;
   }

   // is site (row, col) full? e.g. does it connect to the top pseudo site?
   public boolean isFull(int row, int col) {
       validate(row, col);
       int p = row * grid.length + col + 1;
       return grid[row][col] == 1 && uf.connected(p, 0);
   }

   // number of open sites
   public int numberOfOpenSites() {
       return open;
   }

   // does the system percolate?
   public boolean percolates() {
       return uf.connected(0, grid.length * grid.length);
   }

    /** Checks that the given dimensions are within the range given to the
     * constructor upon initiation.
     *
     * @param row
     * @param col
     * @throws IllegalArgumentException unless row and col are within the bounds.
     */
   private void validate(int row, int col) {
        if (Math.min(row, col) < 0 || Math.max(row, col) >= grid.length) {
            throw new IllegalArgumentException("One of your dimensions "
                + "(" + row + ", " + col + ") is outside of the prescribed bounds");
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
       Percolation perc = new Percolation(4);

      // TODO 1, 1 is upper left
       perc.open(0, 1);
       perc.open(1,1);
       perc.open(0, 0);
       System.out.println(perc);
       System.out.println("openSites: " + perc.numberOfOpenSites());
       System.out.println("count: " + perc.uf.count());
       System.out.println("isFull? " + perc.isFull(1,3));

       perc.open(2, 2);
       perc.open(2, 1);
       System.out.println(perc);
       System.out.println("openSites: " + perc.numberOfOpenSites());
       System.out.println("count: " + perc.uf.count());
       System.out.println("isFull? " + perc.isFull(1,3));
       System.out.println("percolates?: " + perc.percolates());

       perc.open(2, 3);
       perc.open(3,3);
       System.out.println(perc);
       System.out.println("openSites: " + perc.numberOfOpenSites());
       System.out.println("count: " + perc.uf.count());
       System.out.println("percolates?: " + perc.percolates());


   }
}
