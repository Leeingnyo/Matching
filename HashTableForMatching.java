
public class HashTableForMatching<K extends Comparable<K>, V> extends MyHashTable<K, V> {
	public MyAVLTree<K, V> indexOf(int index){
		return hashtable.get(index % SIZE);
	}
}
