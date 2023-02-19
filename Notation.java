import java.util.ArrayList;

/**
 * This class contains methods that convert strings to infix or postfix form and evaluate postfix.
 * @author rogeliobecerra
 *
 */
public class Notation {

	public Notation() {}
	
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix - string to be converted into postfix
	 * @return postfix version of infix 
	 * @throws QueueOverflowException - occurs when a enqueue method is called on a full queue
	 * @throws StackOverflowException - occurs when a push method is called on a full stack
	 * @throws StackUnderflowException - occurs when a top or pop method is called on an empty stack
	 * @throws InvalidNotationFormatException - occurs when a Notation format is incorrect
	 */
	public static String convertInfixToPostfix(String infix) throws QueueOverflowException, StackOverflowException, StackUnderflowException, InvalidNotationFormatException {
		
		int size = infix.length();
		MyStack<Character> stack = new MyStack<>(size);
		MyQueue<Character> queue = new MyQueue<>(size);
		
		int front = 0;
		int end = 0;
		boolean invalid = false;
		
		for(int i =0; i < infix.length();i++) {
			if(infix.charAt(i) == '(')
				front++;
			if(infix.charAt(i) == ')')
				end++;
		}
		
		if(front != end)
			throw new InvalidNotationFormatException();
		
		
		
		for(int i = 0; i < infix.length();i++) 
		{
			char currentChar = infix.charAt(i);
			
			//if current char is a digit add to queue
			if(Character.isDigit(currentChar)) {
				queue.enqueue(currentChar);
			}
			
			
			// if current char is '('
			if(currentChar == '(') 
			{
				stack.push('(');
			}
			else if(currentChar == ')')
			{
					boolean flag = false;
					while(!(flag)) {
						if(stack.top() != '(')
							queue.enqueue(stack.pop());
						else {
							stack.pop();
							flag = true;
						}	
					}
			}
		
			
			
			// first priority operators *, /
			if(currentChar == '*' || currentChar == '/') {
				
				// add to stack if a '(' is at the top of the stack
				if(stack.top() == '(')
					stack.push(currentChar);
				
				// add to stack if stack top has a operation with the same level of priority
				else if(currentChar == stack.top()) {
					queue.enqueue(currentChar);
				}
				
				else if((currentChar == '*' && stack.top() == '/') || (currentChar == '/' && stack.top() == '+') )
				{
					queue.enqueue(stack.pop());
					stack.push(currentChar);
				}
				
				//add when top of stack is of lower priority
				else if(stack.top() == '+' || stack.top() == '-')
					stack.push(currentChar);
				
			}
			
			
			//  second priority operators +, -
			if(currentChar == '+' || currentChar == '-') {
				
				// add to stack if a '(' is at the top of the stack
				if(stack.top() == '(')
					stack.push(currentChar);
				
				// add to stack if stack top has a operation with the same level of priority
				else if(currentChar == stack.top()) {
					queue.enqueue(currentChar);
				}
				else if((currentChar == '+' && stack.top() == '-') || (currentChar == '-' && stack.top() == '+') )
				{
					queue.enqueue(stack.pop());
					stack.push(currentChar);
				}
				// add to stack if stack top has an operation of a higher level, add stack.top to queue and remove then add current to stack
				else if(stack.top() == '*' || stack.top() == '/') {
					queue.enqueue(stack.pop());
					if(stack.top() == '+' || stack.top() == '-')
						stack.push(currentChar);
						
				}
			}
			
			
			
			
		} 					// end of for-loop
		
		while(!(stack.isEmpty())){
			if(stack.top() != '(')
			queue.enqueue(stack.pop());
			else
				stack.pop();
		}
		
		return queue.toString();
		
	}

	
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param Postfix - String to be converted into infix
	 * @return string of postfix converted into infix
	 * @throws StackOverflowException - occurs when a push method is called on a full stack
	 * @throws StackUnderflowException - occurs when a top or pop method is called on an empty stack
	 * @throws InvalidNotationFormatException - occurs when a Notation format is incorrect
	 */
	public static String convertPostfixToInfix(String Postfix) throws StackOverflowException, StackUnderflowException, InvalidNotationFormatException {
		
		int size = Postfix.length();
		MyStack<String> stack = new MyStack<>(size);
		
		
		//when current is a digit
		for(int i = 0; i < size; i++) {
			char currentChar = Postfix.charAt(i);
			
			if(Character.isDigit(currentChar)) {
				String temp = "";
				temp += currentChar;
				stack.push(temp);
			}
			
			//when current is an operation
			if(currentChar == '+' || currentChar == '-' || currentChar == '*' ||currentChar == '/') 
			{
				String temp = null;
				
				if(stack.filledSize >= 2) {
				
					String b = stack.pop();
					String a = stack.pop();
					
					temp = "("+ a + currentChar + b + ")";
				
				}
				else
					throw new InvalidNotationFormatException();
				
				stack.push(temp);
			}
			
		}
		
		if(stack.filledSize > 1)
			throw new InvalidNotationFormatException();
		else
		return stack.toString();
	}
	
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param PostfixExpr - Postfixed to be solved
	 * @return a double with the answer to the postfix
	 * @throws StackOverflowException - occurs when a push method is called on a full stack
	 * @throws InvalidNotationFormatException - occurs when a Notation format is incorrect
	 * @throws StackUnderflowException - occurs when a top or pop method is called on an empty stack
	 */
	public static double evaluatePostfixExpression(String PostfixExpr) throws StackOverflowException, InvalidNotationFormatException, StackUnderflowException {
		
		int size = PostfixExpr.length();
		MyStack<Double> stack = new MyStack<>();
		
		//add digits to stack
		for(int i = 0; i < size; i++) {
			char current = PostfixExpr.charAt(i);
			
			String currentChar = "";
			currentChar += PostfixExpr.charAt(i);
			
			if(Character.isDigit(current)) {
				stack.push(Double.parseDouble(currentChar));
			}
			
			//does math
			if(current == '+' || current == '-' || current == '*' ||current == '/') {
				
				double value =0;
				
				if(stack.filledSize >=2) {
					double b = stack.pop();
					double a = stack.pop();
					
					if(current == '+') 
						value = a + b;
					else if(current == '-') 
						value = a - b;
					else if(current == '*') 
						value = a * b;
					else if(current == '/') 
						value = a / b;
				}
				else
					throw new InvalidNotationFormatException();
				
				stack.push(value);
			}	
			
		}
		
		if(stack.filledSize > 1)
			throw new InvalidNotationFormatException();
		else
		return stack.top();
		
	}
	
	

	


	
	
	
}
