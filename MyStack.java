import java.util.ArrayList;

/**
 * MyStack will implement the Stack Interface. This class will work as a Stack.
 * @author rogeliobecerra
 *
 * @param <T> data type for Stack
 */
public class MyStack <T> implements StackInterface<T> {

	int size = 0; // do not change outside of Constructors;
	
	int filledSize = 0;
	int emptyIndexPointer = 0;
	
	Object[] stackArr ;
	
	/**
	 * Constructors
	 */
	public MyStack() {
		this.size = 10;
		stackArr = new Object[10];
	}
	
	/**
	 * Constructor
	 * @param size - size for the stack
	 */
	public MyStack(int size) {
		this.size = size;
		stackArr = new Object[size];
	}
	

	/**
	 * checks if stack is Empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		
		if(filledSize == 0)
			return true;
		
		return false;
	}

	/**
	 * checks if stack is Full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		if(filledSize == size)
			return true;
		return false;
	}

	/**
	 * Removes the item at the top of the stack and returns it
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T pop() throws StackUnderflowException {
		
		if(filledSize != 0) {
			emptyIndexPointer--;
			filledSize--;
			return (T) stackArr[emptyIndexPointer];
		}
		else 
			throw new StackUnderflowException();
		
	}

	
	/**
	 * returns the top item in the stack 
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(filledSize != 0) {
		return (T) stackArr[emptyIndexPointer-1];
		}
		else
			throw new StackUnderflowException();
	}

	
	/**
	 * returns the size of the stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size() {
		
		return filledSize;
	}

	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		
		if(emptyIndexPointer < size) {
			stackArr[emptyIndexPointer] = e;
			
			emptyIndexPointer++;
			filledSize++;
			return true;
		}
		else {
			throw new StackOverflowException();
		}
		
	}

	/**
	 * Returns the elements of the Stack in a string from bottom to top
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		
		String m = "";
		
		for(int i = 0; i < filledSize;i++) {
			m += stackArr[i];
		}
		return m;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack
	 * Place the delimiter between all elements of the Stack
	 * @param delimiter - A string to add between elements 
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		
		String m = "";
		
		for(int i = 0; i < filledSize;i++) {
			if(i < filledSize - 1)
				m += stackArr[i] + " ";
			else
				m += stackArr[i];
		}
		m = m.replace(" ", delimiter);
		return m;
	}

	/**
	  * Fills the Stack with the elements of the ArrayList
	  * @param list elements to be added to the Stack from bottom to top
	  * @throws StackOverflowException if stack gets full
	  */
	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		
		ArrayList<T> copyList = new ArrayList<>(list);
		
		for(int i = 0; i < copyList.size();i++) {
		this.push(copyList.get(i));
		}
		
		
	}

	
	
}
