
public class Evaluate {
	private static Stack s;
	private static Queue input;
	protected static Queue output;
	
	
	public static String initialize(String args){
		args=args.replaceAll("\\b--", "+"); //We replace everything with a negative to -- this way we avoid errors and make it clear when a number is negative
		args=args.replaceAll("\\b-", "+-"); //We replace everything with a negative to +- this way we avoid errors and make it clear when a number is negative
		String[] tokens =args.split("(?<=[+*/x=()])|((?<=-)(?!\\d))|(?=[()\\-+*x/=])"); //We split the values to make them into tokens 
		input=new Queue<String>();
		s=new Stack<String>();
		input.inputQueue(tokens);
		output=In2post(input, s);
		return evaluate(output);
	}
	
	public static Queue<String> In2post(Queue<String> input, Stack<String> s){
		Queue<String> postFix=new Queue<String>();
		String x;
			while(!input.isEmpty()){ //Until the input is empty we iterate
				x=(input.dequeue()); //We continually assign x to be the next incremental character
				//System.out.print(x);
				if(isNumeric(x)){ //If the value x is a digit we know that it will stay in it's original place
					postFix.add(x); //We add it to the output queue
				}else{ //If x is not a digit we know it is an operator so we add a space for formatting and send to isOperator
					isOperator(x, postFix, s);
				}
			}		
		while(!s.isEmpty()){ //We remove all the elements remaining on the stack and add them to the output queue
			postFix.add(s.pop());
		}
		return postFix;
	}
	
	public static boolean isNumeric(String str)  {  
	  try{  
	    Double.parseDouble(str);  
	  } catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static void isOperator(String x, Queue<String> output, Stack<String> s){ 
		int current;int last; 
		if(s.isEmpty()){ //We use this just to manage null pointer exceptions, cause if a stack is empty we can't peek
			s.push(x); //We push it to the stack and compare it to itself if there's nothing in the stack
		}else{
		while(!s.isEmpty()){
		current=checkPrecedence(x); last=checkPrecedence(s.peek()); //Assigning precedence
			if(current>last){ //We know that if the current is greater than the last then we can add it to the stack
				break;
			}else{
				output.add(s.pop()); //If not we add the operator to the output cause it's time is up //We add a space to maintain a nice format
			}
		}
		s.push(x);
		}
	}
	
	public static String evaluate(Queue<String> output){ 
		Stack<Double> s=new Stack<Double>(); //We make a new stack that we will use to store our numerical values
		String sResult="";
		while(!output.isEmpty()){ //We iterate until the output queue is empty - meaning that we've check all the characters
			if(isNumeric(output.peek())){ //If the character coming up is a digit we know that it needs to get parsed so we sent it parseToDouble
				double value=parseTodouble(output);
				s.push(value);
			}else{
				s.push(operator(s, output)); //This is where we push the result of the operation
			}
		}
		
			if(s.peek()%1==0){ //If the value is an integer we don't want to print it with an ugly decimal place so we use the following lines to make it look nicer
				sResult=Double.toString(s.peek());
			}else{
				sResult=Double.toString(s.peek());
			}
		return sResult;
	}
	
	public static double operator(Stack<Double> s, Queue<String> output){ //This method evaluates the values that have been passed
		int y=checkPrecedence(output.peek()); //Checks to see whether the value is an operator or a space - just for redundancy
		double value=0; //We first initialize our variable to the value of 0
		if(y!=3){
			switch(output.peek()){ //We determine which operator it is and we perform the necessary operation, we pop the two most recent values on the stack and evaluate them - pushing the result onto the stack
			case "+":
				value=(s.pop()+s.pop());
				output.dequeue();
				break;
			case "-":
				value=(-s.pop()+s.pop());
				output.dequeue();
				break;
			case "x":
				value=(s.pop()*s.pop());
				output.dequeue();
				break;
			case "*":
				value=(s.pop()*s.pop());
				output.dequeue();
				break;
			case "/":
				value=(1/s.pop())*s.pop();
				output.dequeue();
				break;
		}
		}else{
			output.dequeue(); //If it's a space we dequeue that bad boy
		}
		return value; //We return the value that came about as a result of our operations - to be pushed onto stack after return
	}
	
	public static double parseTodouble(Queue<String> output){ //Parses consecutive characters ito doubles for evaluation
		double value=Double.parseDouble(output.dequeue());
		return value;
	}
	
	public static int checkPrecedence(String x){ //Assigns a precedence value to all the following operators higher precedence is better, in the default case - returns 3 - maintains the place of a variable it doesn't know what to do with
		switch(x){
		case "x":
			return 1;
		case "*":
			return 1;
		case "/":
			return 1;
		case "+":
			return 0;
		case "-":
			return 0;
		}
		return 3;
	}
}
