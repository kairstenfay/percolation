import java.util.Arrays;

public class Percolation {

   private int[][] grid;

   // create n-by-n grid, with all sites blocked
   public Percolation(int n) {
       if (n <= 0) {
           throw new IllegalArgumentException("n must be greater than zero.");
       }

       grid = new int[n][n];

       for (int i = 0; i < n; i++) {
           grid[i] = new int[n];
       }
   }

   // open site (row, col) if it is not open already
   public void open(int row, int col) {
        validateDimensions(row);
        validateDimensions(col);

        grid[row][col] = 1;

        // edge cases: top row, bottom row, leftmost column, rightmost column
       if (row != 0) {  // not the top row
           grid[row - 1][col] = 1;
       }

       if (row != grid.length - 1) {
           grid[row + 1][col] = 1;
       }

       if (col != 0) {
           grid[row][col - 1] = 1;
       }

       if (col != grid.length - 1) {
           grid[row][col + 1] = 1;
       }
   }

   // is site (row, col) open?
   public boolean isOpen(int row, int col) {
       validateDimensions(row);
       validateDimensions(col);

       return grid[row][col] == 1;
   }

   // is site (row, col) full?
   public boolean isFull(int row, int col) {
       validateDimensions(row);
       validateDimensions(col);

       return grid[row][col] == 0;
   }

   // number of open sites
   public int numberOfOpenSites() {
       int cumSum = 0;

       for (int i = 0; i < grid.length; i++) {
           for (int j = 0; j < grid.length; j++) {
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
   private void validateDimensions(int n) {
        if (n < 0 || n >= grid.length) {
            throw new IllegalArgumentException(n + " is outside of the prescribed bounds");
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
      Percolation perc = new Percolation(5);

      // TODO 1, 1 is upper left
      perc.open(1,1);
      System.out.println(perc);
      System.out.println(perc.numberOfOpenSites());
   }
}


/*
Performance requirements.  The constructor should take time proportional to n2;
all methods should take constant time plus a constant number of calls to the
union–find methods union(), find(), connected(), and count().
 */