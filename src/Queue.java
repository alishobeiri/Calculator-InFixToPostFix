//Inspiration from: http://codereview.stackexchange.com/questions/54977/implement-a-queue-using-a-linked-list
public class Queue<T> {
	public Node first, last; //Similar to a stack but now we keep track of the front and back of the list
	public int size;
	
	public Queue(){ //Initializing everything to 0 and null
		first=null;
		last=null;
		size=0;
	}
	
	public void add(T x){ //To add to queue we need to make sure that all the nodes are connected to each so the first node -> last node
		Node<T> newNode=new Node<T>(x); //We create a new node value and intialize it accordingly 
		if(size==0){ //If the size is 0 we know that we need to build our in a way that the first and last node relate to each other, so we do this by creating a node and assigning the value to both
			first=newNode;
			last=newNode;
			size++;
		}else{ //If the size is not 0 the first and last have already been linked and we can just add to the last node
			last.next=newNode; //We assign last.next to point back to newNode
			last=newNode; //We make the last node equal to the newNode as well
			last.next=null;
			size++;
		}
	}
	
	public void addFirst(T x){ //To add to queue we need to make sure that all the nodes are connected to each so the first node -> last node
		Node<T> newNode=new Node(x); //We create a new node value and intialize it accordingly 
		if(size==0){ //If the size is 0 we know that we need to build our in a way that the first and last node relate to each other, so we do this by creating a node and assigning the value to both
			first=newNode;
			last=newNode;
			size++;
		}else{ //If the size is not 0 the first and last have already been linked and we can just add to the last node
			last.next=newNode; //We assign last.next to point back to newNode
			last=newNode; //We make the last node equal to the newNode as well
			last.next=null;
			size++;
		}
	}
	
	public T dequeue(){
        if (this.isEmpty()){ //Good to use for debugging, can check when anything is empty
            System.out.println("Queue is empty");
            return null;
        } 
        T x=((T)(first.elem)); //we save the value in the first element as x
        first = first.next; //We essentially remove the first element by assigning it to the value after it
        size--;
        return x;
	}
	
	public void inputQueue(String args[]){ //This is an initializer that takes the arguments; prints them and fills up the queue with them
		for(int i=0;i<args.length;i++){ //Adding all of the char elements to a queue
			addFirst((T) args[i]);
		}
	}
	
	public boolean isEmpty(){
		return size==0; //If size is 0 we return true if not we return false
	}
	
	public T peek(){
		return (T)first.elem; //Returns the first elem
	}
	
}
