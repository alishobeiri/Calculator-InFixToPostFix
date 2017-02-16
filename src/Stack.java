//Inspiration from: http://eddmann.com/posts/implementing-a-stack-in-java-using-arrays-and-linked-lists/
public class Stack<T> {
	private Node first; 
	public int size;
	
	public void push(T x){
		Node<T> current=first; //When we wanna push a value we know that we need to add a node - we save the value of current node
		first=new Node<T>(x); //We make a new node and assign it to first
		first.next=current; //We set the node after our current to be our last node ie sending it back in the line 
		size++; //We increment the size by one for our isEmpty
	}
	
	public Stack(){ 
		first=null; //Initializing everything to 0 and null
		size=0;
	}

	@SuppressWarnings("unchecked")
	public T pop(){
		T x=null;
		if(size==0){ //Used to debug and helps to understand if stack is empty
			System.out.println("Stack is empty");
			return x;
		}else{ //When we wanna pop we have to remove the first element so we assign the first elem to equal the element right after it
			T value;
			value=(T) (first.elem);
			first=first.next;
			size--;
			return value;
		}
	}
	
	public boolean isEmpty(){
		return size==0; //if size is 0 we return true if not false
	}
	
	@SuppressWarnings("unchecked")
	public T peek(){
		return ((T)(first.elem)); //We return the first element in the stack
	}
	
}

