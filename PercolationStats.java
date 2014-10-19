public class PercolationStats {

	private double[] stats;

  	public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
	{
		if ( N <= 0 || T <= 0 ) throw new java.lang.IllegalArgumentException("Illegal argument");

		stats = new double[T];
		
		for ( int i = 0; i < T; i ++ ) {
			double count = 0;
			Percolation p = new Percolation(N);
			while ( ! p.percolates() ) {
				int x = StdRandom.uniform(1, N+1);
				int y = StdRandom.uniform(1, N+1);
				if ( p.isOpen(x, y) ) continue;
				
				p.open(x, y);
				count ++;
			}
			stats[i] = count / (N*N);
		}
	}

	public double mean()                      // sample mean of percolation threshold
	{
		int n = stats.length;
		double total = 0;
		for ( int i = 0; i < n; i ++ ) {
			total += stats[i];
		}
		return total / n;
	}

	public double stddev()                    // sample standard deviation of percolation threshold
	{
		int n = stats.length;
		double u = mean();
		double total = 0;
		for ( int i = 0; i < n; i ++ ) {
			total += ( stats[i] - u ) * ( stats[i] - u );
		}
		
		return total / (n-1);
	}

    public double confidenceLo()              // low  endpoint of 95% confidence interval
	{
		return mean() - stddev();
	
	}

    public double confidenceHi()              // high endpoint of 95% confidence interval
	{
		return mean() + stddev();
	}

	public static void main(String[] args)    // test client (described below)
	{
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(N, T);

		StdOut.println("mean = " + ps.mean());
		StdOut.println("stddev = " + ps.stddev());
		StdOut.println("95% confidence interval = " + ps.confidenceLo() + "," + ps.confidenceHi());
	}
}
