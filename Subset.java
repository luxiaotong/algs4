public class Subset<Item>
{
	public static void main(String[] args)   // unit testing
	{
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
			rq.enqueue(item);
        }
		
		StdOut.println("Result:");
		
		while (!rq.isEmpty()) {
			String item = rq.dequeue();
			StdOut.println(item);
		}
	}
}

