import java.util.ArrayList;

/**
 * MyQueue will implement the QueueInterface. This class will work as a Queue.
 * @author rogeliobecerra
 *
 * @param <T> data type for MyQueue
 */
public class MyQueue <T> implements QueueInterface<T> {

	int size;
	
	int filledSize = 0;
	int frontIndex = 0;
	int rearIndex = 0;
	
	Object[] arr;
	
	/**
	 * default constructor - uses a default as the size of the queue
	 */
	public MyQueue() {
		this.size = 10;
		arr = new Object[size];
	}
	
	/**
	 * constructor - takes an int as the size of the queue
	 * @param size - size for queue
	 */
	public MyQueue(int size) {
		this.size = size;
		arr = new Object[size];
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		if(filledSize == 0)
			return true;
		
		return false;
	}

	/**
	 * Determines of the Queue is Full
	 * @return true if Queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		if(((rearIndex) % size) == frontIndex)
			return true;
		return false;
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		
		if(filledSize != 0) {
		T t = (T) arr[frontIndex];
		frontIndex = ((frontIndex + 1)% size);
		filledSize--;
		return (T) t;
		}
		else
			throw new QueueUnderflowException();
	}

	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return filledSize;
	}

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(filledSize == 0) {
			arr[0] = e;
			
			filledSize++;
			rearIndex = ((rearIndex + 1) % size);
			return true;
		}
		else if((filledSize != 0) && (isFull() == false)) {
			arr[rearIndex % size] = e;
			
			filledSize++;
			rearIndex = ((rearIndex + 1) % size);
			return true;
		}
		else
			throw new QueueOverflowException();
	}

	
	/**
	 * Returns the string representation of the elements in the Queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		
		String t = "";
		
		for(int i = 0; i < filledSize; i++)
		{
			t += arr[frontIndex + i % size];
		}
		
		return t;
	}
	
	
	/**
	 * Returns the string representation of the elements in the Queue, Place the delimiter between all elements of the Queue
	 * @param delimiter - A string to add between elements 
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		
		String t = "";
		
		for(int i = 0; i < filledSize; i++)
		{
			if(i < filledSize - 1)
				t += arr[frontIndex + i % size] + " ";
			else
				t += arr[frontIndex + i % size];
		}
		t = t.replace(" ", delimiter);
		
		return t;
		
	}

	
	/**
	 * Fills the Queue with the elements of the ArrayList
	 * @param list elements to be added to the Queue
	 * @throws QueueOverflowException if queue is full
	 */
	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		
		ArrayList<T> listcopy = new ArrayList<>(list);
		
		for(int i = 0; i < listcopy.size(); i++) {
			this.enqueue(listcopy.get(i));
		}
		
	}

}
