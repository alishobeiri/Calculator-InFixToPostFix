
public class In2pJ {
	public static void main(String[] args){
		Queue<Character> input=new Queue<Character>(); //Create the input queue
		Stack<Character> s=new Stack<Character>(); //Create the operator stack
		input.inputQueue(args); //We pass the args to input queue which will parse and fill the queue
		int y=input.size;
		Queue<Character> output=new Queue<Character>();//We create an output queue that will hold the desired output
		output=In2post(input,s, output);//We assign the output queue to the return value In2post which is a properly formatted output queue
		int x=output.size; //We save the size of output so it's easier to iterate
		if(x>0){
			for(int i=1;i<args.length;i++){
				System.out.print(args[i]+" ");
			}
			evaluate(output);
		}
		Evaluate test = new Evaluate();
	}
	
	public static Queue<Character> In2post(Queue<Character> input, Stack<Character> s, Queue<Character> output){
		char x;
			while(!input.isEmpty()){ //Until the input is empty we iterate
				x=(input.dequeue()); //We continually assign x to be the next incremental character
				//System.out.print(x);
				if(Character.isDigit(x)){ //If the value x is a digit we know that it will stay in it's original place
					output.add(x); //We add it to the output queue
				}else{
					output.add((char)32); //If x is not a digit we know it is an operator so we add a space for formatting and send to isOperator
					isOperator(x, output, s);
				}
			}		
		while(!s.isEmpty()){ //We remove all the elements remaining on the stack and add them to the output queue
			output.add((char)32);
			output.add(s.pop());
		}
		return output;
	}
	
	public static void isOperator(char x, Queue<Character> output, Stack<Character> s){ 
		int current;int last; 
		if(s.isEmpty()){ //We use this just to manage null pointer exceptions, cause if a stack is empty we can't peek
			s.push(x); //We push it to the stack and compare it to itself if there's nothing in the stack
		}else{
		while(!s.isEmpty()){
		current=checkPrecedence(x); last=checkPrecedence(s.peek()); //Assigning precedence
			if(current>last){ //We know that if the current is greater than the last then we can add it to the stack
				break;
			}else{
				output.add(s.pop()); //If not we add the operator to the output cause it's time is up
				output.add((char)32); //We add a space to maintain a nice format
			}
		}
		s.push(x);
		}
	}
	
	public static void evaluate(Queue<Character> output){ 
		Stack<Double> s=new Stack<Double>(); //We make a new stack that we will use to store our numerical values
		while(!output.isEmpty()){ //We iterate until the output queue is empty - meaning that we've check all the characters
			if(Character.isDigit(output.peek())){ //If the character coming up is a digit we know that it needs to get parsed so we sent it parseToDouble
				s.push(parseTodouble(output));
			}else if(output.peek()==(char)32){ //If the character is a space we know that it doesn't factor into our calculation and so we pop it
				output.dequeue();
			}else{
				s.push(operator(s, output)); //This is where we push the result of the operation
			}
		}
			if(s.peek()%1==0){ //If the value is an integer we don't want to print it with an ugly decimal place so we use the following lines to make it look nicer
				System.out.println(((" = " + s.peek().intValue())));
			}else{
				System.out.println(" = " + s.peek());
			}
		
	}
	
	public static double operator(Stack<Double> s, Queue<Character> output){ //This method evaluates the values that have been passed
		int y=checkPrecedence(output.peek()); //Checks to see whether the value is an operator or a space - just for redundancy
		double value=0; //We first initialize our variable to the value of 0
		if(y!=3){
			switch(output.peek()){ //We determine which operator it is and we perform the necessary operation, we pop the two most recent values on the stack and evaluate them - pushing the result onto the stack
			case '+':
				value=(s.pop()+s.pop());
				output.dequeue();
				break;
			case '-':
				value=(-s.pop()+s.pop());
				output.dequeue();
				break;
			case 'x':
				value=(s.pop()*s.pop());
				output.dequeue();
				break;
			case '*':
				value=(s.pop()*s.pop());
				output.dequeue();
				break;
			case '/':
				value=(1/s.pop())*s.pop();
				output.dequeue();
				break;
		}
		}else{
			output.dequeue(); //If it's a space we dequeue that bad boy
		}
		return value; //We return the value that came about as a result of our operations - to be pushed onto stack after return
	}
	
	public static double parseTodouble(Queue<Character> output){ //Parses consecutive characters ito doubles for evaluation
		char y;
		StringBuilder sb=new StringBuilder(); //Uses the string builder to parse characters to digits, by continually appending and then using the toString method
		while(!(output.isEmpty())){
			if(!Character.isDigit(output.peek())){
				break;
			}
			y=output.dequeue();
			sb.append(y);
		}
		double value=Double.parseDouble(sb.toString());
		return value;
	}
	
	public static int checkPrecedence(char x){ //Assigns a precedence value to all the following operators higher precedence is better, in the default case - returns 3 - maintains the place of a variable it doesn't know what to do with
		switch(x){
		case 'x':
			return 1;
		case '*':
			return 1;
		case '/':
			return 1;
		case '+':
			return 0;
		case '-':
			return 0;
		}
		return 3;
	}
	
	
}
