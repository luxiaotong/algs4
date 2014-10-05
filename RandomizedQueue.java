import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> /*implements Iterable<Item>*/ {
	/*
   public Item sample()                     // return (but do not delete) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
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

	public void check()
	{
		Node tmp;
	
		if ( isEmpty() ) return;

		StdOut.println("Queue:");
		for ( tmp = first; tmp != null; tmp = tmp.next ) {
			StdOut.println(tmp.item);
		}
		
		StdOut.println("first: " + first.item);
		StdOut.println("last : " + last.item);
		StdOut.println("size : " + size());
	}
   	
	public static void main(String[] args)   // unit testing
	{
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) rq.enqueue(item);
            else if (!rq.isEmpty()) StdOut.println(rq.dequeue());
			
			rq.check();
        }
		/*
		Iterator<String> it = d.iterator();
		for(String idq : d) {
			StdOut.println(idq);}
		*/
        StdOut.println("(" + rq.size() + " left on deque)");
	}

}
