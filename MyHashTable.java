import java.util.ArrayList;


public class MyHashTable<K extends Comparable<K>, V> {
	
	protected ArrayList<MyAVLTree<K, V>> hashtable;
	protected final static int SIZE = 100;
	
	public MyHashTable() {
		hashtable = new ArrayList<MyAVLTree<K,V>>(SIZE);
		for (int i = 0; i < SIZE; i++){
			hashtable.add(new MyAVLTree<K, V>());
		}
	}
	
	public V put(K key, V value){
		hashtable.get(key.hashCode() % SIZE).insert(key, value);
		return value;
	}
	
	public V remove(K key, V value){
		throw new UnsupportedOperationException();
		//과제에는 필요가 없으니 에러처리하였습니다.
	}
	
	public MyLinkedList<V> getItems(K key){
		return hashtable.get(key.hashCode() % SIZE).search(key);
	}
	
	public V get(K key){
		MyLinkedList<V> items = getItems(key);
		return items.getTail().getValue();
	}
}
