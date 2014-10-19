import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
	/*
	*/
	private Node first 	= null;
	private Node last 	= null;
	private int  N 		= 0;
	
   	public RandomizedQueue()                 // construct an empty randomized queue
	{
		first 	= null;
		last	= null;
		N		= 0;
	}

	private class Node
	{
		Item item;
		Node next;
	}
	
	public boolean isEmpty()                 // is the queue empty?
	{
		return first == null;
	}

	public int size()                        // return the number of items on the queue
	{
		return N;
	}
   	
	public void enqueue(Item item)           // add the item
	{
		Node oldlast 	= last;
		last 			= new Node();
		last.item 		= item;
		last.next 		= null;
		if ( isEmpty() ) first = last;
		else oldlast.next = last;

		N++;
	}
   
	public Item dequeue()                    // delete and return a random item
	{
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		
		int n 		= size();
		int rand 	= StdRandom.uniform(n);
		int i 		= 0;
		Node pop 	= first;
		Node prev 	= first;

		for ( i = 0; i < rand; i ++ ) {
			prev 	= pop;
			pop 	= pop.next;
		}
		Item item = pop.item;
		prev.next = pop.next;

		if ( i == 0 ) first = first.next;
		else if ( i == n-1 ) last = prev;
		
		N--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
	}

   	public Item sample()                     // return (but do not delete) a random item
	{
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		
		int n 		= size();
		int rand 	= StdRandom.uniform(n);
		int i 		= 0;
		Node pop 	= first;
		Node prev 	= first;

		for ( i = 0; i < rand; i ++ ) {
			pop 	= pop.next;
		}
		Item item = pop.item;
        return item;

	}

	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;
		
		public void remove() { throw new UnsupportedOperationException("Not support"); }

		public boolean hasNext() { return current != null; }
		
		public Item next()
		{
			if (!hasNext()) throw new NoSuchElementException();

			Item item 	= current.item;
			current 	= current.next;
			return item;
		}
	}
   	
	public static void main(String[] args)   // unit testing
	{
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) rq.enqueue(item);
            //else if (!rq.isEmpty()) StdOut.println(rq.dequeue());
            //else if (!rq.isEmpty()) StdOut.println(rq.sample());
			else break;
			
        }
		for(String str : rq)
			StdOut.println(str);
        StdOut.println("(" + rq.size() + " left on deque)");
	}

}
