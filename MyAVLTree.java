
class AVLTreeNode<K extends Comparable<K>, V>{
	private K key;
	private MyLinkedList<V> item;
	private AVLTreeNode<K, V> left;
	private AVLTreeNode<K, V> right;
	int height;

	public AVLTreeNode(K key, V value) {
		this.key = key;
		item = new MyLinkedList<V>();
		item.add(value);
		left = null;
		right = null;
		height = 1;
	}
	public K getKey(){
		return key;
	}
	public MyLinkedList<V> getitem(){
		return item;
	}
	public AVLTreeNode<K, V> getLeft(){
		return left;
	}
	public AVLTreeNode<K, V> getRight(){
		return right;
	}
	public void setLeft(AVLTreeNode<K, V> left){
		this.left = left;
	}
	public void setRight(AVLTreeNode<K, V> right){
		this.right = right;
	}
	public int getHeight(){
		return height;
	}
	public void setHeight(){
		int leftHeight = left == null ? 0 : left.getHeight();
		int rightHeight = right == null ? 0 : right.getHeight();
		height = Math.max(leftHeight, rightHeight) + 1;
	}
	private int getBalanceFactor(){
		int leftHeight = left == null ? 0 : left.getHeight();
		int rightHeight = right == null ? 0 : right.getHeight();
		return leftHeight - rightHeight;
	}
	public boolean needBalancing(){
		return Math.abs(getBalanceFactor()) > 1;
	}
	public AVLTreeNode<K, V> doBalancing(){
		if (this.getBalanceFactor() > 0){
			if (this.getBalanceFactor() * left.getBalanceFactor() < 0){
				this.left = left.leftRotate();
				return this.rightRotate();
			} else {
				return this.rightRotate();
			}
		} else {
			if (this.getBalanceFactor() * right.getBalanceFactor() < 0){
				this.right = right.rightRotate();
				return this.leftRotate();
			} else {
				return this.leftRotate();
			}
		}
	}
	private AVLTreeNode<K, V> rightRotate(){
		AVLTreeNode<K, V> leftChild = left;
		AVLTreeNode<K, V> leftRightChild = left.getRight();
		leftChild.setRight(this);
		this.setLeft(leftRightChild);
		this.setHeight();
		leftChild.setHeight();
		return leftChild;
	}
	private AVLTreeNode<K, V> leftRotate(){
		AVLTreeNode<K, V> rightChild = right;
		AVLTreeNode<K, V> rightLeftChild = right.getLeft();
		rightChild.setLeft(this);
		this.setRight(rightLeftChild);
		this.setHeight();
		rightChild.setHeight();
		return rightChild;
	}
	
	@Override
	public String toString(){
		String string = key.toString();
		string += (left != null ? " "+ left.toString() : "");
		string += (right != null ? " "+ right.toString() : "");
		return string;
	}
}

public class MyAVLTree<K extends Comparable<K>, V>{
	AVLTreeNode<K, V> root;
	
	MyAVLTree(){
		root = null;
	}
	
	boolean insert(K key, V value){
		root = insert(root, key, value);
		return true;
	}
	private AVLTreeNode<K, V> insert(AVLTreeNode<K, V> node, K key, V value){
		if (node == null){
			return new AVLTreeNode<K, V>(key, value);
		} else {
			if (key.compareTo(node.getKey()) < 0){
				node.setLeft(insert(node.getLeft(), key, value));
			} else if (key.compareTo(node.getKey()) == 0){
				node.getitem().add(value);
			} else {
				node.setRight(insert(node.getRight(), key, value));
			}
		}
		node.setHeight();
		if (node.needBalancing()){
			return node.doBalancing();
		} else {
			return node;
		}
	}
	
	@Override
	public String toString(){
		if (root != null)
			return root.toString();
		else
			return "EMPTY";
	}
	
	public MyLinkedList<V> search(K key){
		return search(root, key); 
	}
	private MyLinkedList<V> search(AVLTreeNode<K, V> node, K key){
		if (node == null){
			return null;
		} else if (key.compareTo(node.getKey()) < 0){
			return search(node.getLeft(), key);
		} else if (key.compareTo(node.getKey()) > 0) {
			return search(node.getRight(), key);
		} else {
			return node.getitem();
		}
	}
}