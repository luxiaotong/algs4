import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
	private Node first;
	private Node last;
	private int  N;

	public Deque()                           // construct an empty deque
	{
		first 	= null;
		last	= null;
		N		= 0;
	}

	private class Node
	{
		Item item;
		Node next;
		Node prev;
	}
	
	public boolean isEmpty()                 // is the deque empty?
	{
		return first == null;
	}

	public int size()                        // return the number of items on the deque
	{
		return N;
	}
	
	public void addFirst(Item item)          // insert the item at the front
	{
		if ( isEmpty() ) {
			first 			= new Node(); 
			first.item 		= item;
			first.next 		= null;
			first.prev 		= null;

			last 			= first;
		} else {
			Node oldfirst 	= first;
			first 			= new Node(); 
			first.item 		= item;
			first.next 		= oldfirst;
			first.prev		= null;

			oldfirst.prev = first;
		}

		N++;
	}

	public void addLast(Item item)           // insert the item at the end
	{
		if ( isEmpty() ) {
			last 			= new Node(); 
			last.item 		= item;
			last.next 		= null;
			last.prev 		= null;

			first 			= last;
		} else {
			Node oldlast	= last;
			last			= new Node(); 
			last.item 		= item;
			last.next 		= null;
			last.prev		= oldlast;

			oldlast.next	= last;
		}

		N++;
	}

	public Item removeFirst()                // delete and return the item at the front
	{
		if ( isEmpty() ) throw new NoSuchElementException("Deque is empty");
		if ( size() == 1 ) first = last = null;

		Item item 	= first.item;
		first 		= first.next;
		first.prev 	= null;

		N--;

		return item;
	}

	public Item removeLast()                 // delete and return the item at the end
	{
		if ( isEmpty() ) throw new NoSuchElementException("Deque is empty");
		if ( size() == 1 ) first = last = null;

		Item item 	= last.item;
		last 		= last.prev;
		last.next 	= null;

		N--;

		return item;
	}

	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
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
        Deque<String> d = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) d.addFirst(item);
            //else if (!d.isEmpty()) StdOut.print(d.removeFirst() + " ");
			else break;
        }
		Iterator<String> it = d.iterator();
		for(String idq : d) {
			StdOut.println(idq);}
        StdOut.println("(" + d.size() + " left on deque)");
	}

	
}
