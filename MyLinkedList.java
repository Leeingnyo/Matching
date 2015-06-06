
class LinkedListNode<T> {
	private T value;
	private LinkedListNode<T> nextNode;
	LinkedListNode(){
		
	}
	LinkedListNode(T value){
		this.value = value;
	}
	LinkedListNode(T value, LinkedListNode<T> nextNode){
		this.value = value;
		this.nextNode = nextNode;
	}
	void setNextNode(LinkedListNode<T> nextNode){
		this.nextNode = nextNode;
	}
	void setValue(T value){
		this.value = value;
	}
	T getValue(){
		return value;
	} 
	LinkedListNode<T> getNextNode(){
		return nextNode;
	}
}

public class MyLinkedList<T> {
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	
	public MyLinkedList(){
		tail = head = new LinkedListNode<T>();
	}

	public LinkedListNode<T> getHead(){
		return head.getNextNode();
	}
	public LinkedListNode<T> getTail(){
		return tail;
	}
	
	boolean add(T value){
		LinkedListNode<T> Node = new LinkedListNode<T>(value);
		tail.setNextNode(Node);
		tail = Node;
		return true;
	}
	
	boolean remove(T value){
		LinkedListNode<T> prev = head;
		for (LinkedListNode<T> curr = head.getNextNode(); curr != null; prev = curr, curr = curr.getNextNode()){
			if (curr.getValue().equals(value)){
				prev.setNextNode(curr.getNextNode());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		String string = new String();
		for (LinkedListNode<T> curr = head.getNextNode(); curr != null; curr = curr.getNextNode()){
			string += curr.getValue().toString() + (curr.getNextNode() != null ? " " : "");
		}
		return string;
	}
}