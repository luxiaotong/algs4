public class Percolation {

	private WeightedQuickUnionUF wquuf;
	private int grid[][];
	private int square;

	public Percolation(int N)               // create N-by-N grid, with all sites blocked
	{
		wquuf 	= new WeightedQuickUnionUF( N * N + 2 );
		grid 	= new int[N][N];
		square 	= N;

		for ( int i = 0; i < N; i ++ ) {
			for ( int j = 0; j < N; j ++ ) {
				grid[i][j] = 0;
			}
		}
	}
	
	public boolean isOpen(int i, int j)     // is site (row i, column j) open?
	{
		if ( i < 1 || i > square || j < 1 || j > square ) throw new IndexOutOfBoundsException("Outside prescribed range");
		return grid[i-1][j-1] == 1;
	}

	public void open(int i, int j)          // open site (row i, column j) if it is not open already
	{
		if ( i < 1 || i > square || j < 1 || j > square ) throw new IndexOutOfBoundsException("Outside prescribed range");
		grid[--i][--j] = 1;

		if ( i > 0 && grid[i-1][j] > 0 ) //Up
			wquuf.union( xyTo1D(i, j), xyTo1D(i-1, j) );
		if ( i < square - 1 && grid[i+1][j] > 0 ) //Down
			wquuf.union( xyTo1D(i, j), xyTo1D(i+1, j) );
		if ( j > 0 && grid[i][j-1] > 0 ) //Left
			wquuf.union( xyTo1D(i, j), xyTo1D(i, j-1) );
		if ( j < square - 1 && grid[i][j+1] > 0 ) //Right
			wquuf.union( xyTo1D(i, j), xyTo1D(i, j+1) );
		if ( i == 0 ) //Top
			wquuf.union( xyTo1D(i, j), 0 );
		if ( i == square - 1 ) //Bottom
			wquuf.union( xyTo1D(i, j), square*square+1 );
	}

	private int xyTo1D(int x, int y)
	{
		return x * square + y;
	}

	public boolean isFull(int i, int j)     // is site (row i, column j) full?
	{
		if ( i < 1 || i > square || j < 1 || j > square ) throw new IndexOutOfBoundsException("Outside prescribed range");

		return wquuf.connected( 0, xyTo1D(--i, --j) );
	}

	public boolean percolates()             // does the system percolate?
	{
		return wquuf.connected( 0, square*square+1 );
	}
	
	public void printAll()
	{
		for ( int i = 0; i < square; i ++ ) {
			for ( int j = 0; j < square; j ++ ) {
				StdOut.print(grid[i][j]+" ");
			}
			StdOut.println();
		}
	}

   	public static void main(String[] args)   // test client (optional)
	{
		Percolation p = new Percolation(5);
		p.open(1,2);
		p.open(2,2);
		p.open(3,2);
		p.open(3,3);
		p.open(4,3);
		p.open(5,3);
		StdOut.println("percolates: " + p.percolates());
		p.printAll();
	}
}
